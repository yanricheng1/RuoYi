package com.ruoyi.auth.web.sso.service;

import com.ruoyi.auth.web.sso.PasswordEncryptStrategy;
import com.ruoyi.auth.web.sso.strategy.PasswordEncryptorType;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.constant.ShiroConstants;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.event.BizEventDispatcher;
import com.ruoyi.common.event.BizTopicType;
import com.ruoyi.common.event.msg.LoginInfo;
import com.ruoyi.common.exception.user.UserPasswordNotMatchException;
import com.ruoyi.common.exception.user.UserPasswordRetryLimitExceedException;
import com.ruoyi.common.redis.RedisCache;
import com.ruoyi.common.redis.ShiroRedisCacheManager;
import com.ruoyi.common.utils.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * 登录密码方法
 *
 * @author ruoyi
 */
@Component
public class SysPasswordService {
    @Autowired
    private ShiroRedisCacheManager cacheManager;

    private RedisCache<String, Integer> loginRecordCache;

    @Value(value = "${user.password.maxRetryCount}")
    private String maxRetryCount;

    @Resource
    private PasswordEncryptStrategy passwordEncryptStrategy;

    @PostConstruct
    public void init() {
        loginRecordCache = cacheManager.getCache(ShiroConstants.LOGIN_RECORD_CACHE);
    }

    public void validate(SysUser user, String password) {
        String loginName = user.getLoginName();

        Integer retryCount = loginRecordCache.get(loginName);
        if (retryCount == null) {
            retryCount = new Integer(0);
            loginRecordCache.put(loginName, retryCount);
        }
        retryCount = retryCount + 1;

        if (retryCount > Integer.valueOf(maxRetryCount).intValue()) {
            LoginInfo loginInfo = LoginInfo.builder().username(loginName).status(Constants.LOGIN_FAIL)
                    .message(MessageUtils.message("user.password.retry.limit.exceed")).args(new Object[]{maxRetryCount}).build();
            BizEventDispatcher.dispatch(BizTopicType.USER_LOGIN, loginInfo);
            throw new UserPasswordRetryLimitExceedException(Integer.valueOf(maxRetryCount).intValue());
        }

        if (!matches(user, password)) {
            LoginInfo loginInfo = LoginInfo.builder().username(loginName).status(Constants.LOGIN_FAIL)
                    .message(MessageUtils.message("user.password.retry.limit.count")).args(new Object[]{retryCount}).build();
            BizEventDispatcher.dispatch(BizTopicType.USER_LOGIN, loginInfo);
            loginRecordCache.put(loginName, retryCount);
            throw new UserPasswordNotMatchException();
        } else {
            clearLoginRecordCache(loginName);
        }
    }

    public boolean matches(SysUser user, String newPassword) {
        return user.getPassword().equals(encryptPassword(user.getLoginName(), newPassword, user.getSalt()));
    }

    public void clearLoginRecordCache(String loginName) {
        loginRecordCache.remove(loginName);
    }

    public String encryptPassword(String loginName, String password, String salt) {
        return passwordEncryptStrategy.encrypt(PasswordEncryptorType.jdk_md5.name(), loginName + password + salt);
    }
}
