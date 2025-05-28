package com.ruoyi.auth.web.sso.service;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.constant.ShiroConstants;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.enums.UserStatus;
import com.ruoyi.common.event.BizEventDispatcher;
import com.ruoyi.common.event.BizTopicType;
import com.ruoyi.common.event.msg.LoginInfo;
import com.ruoyi.common.exception.user.BlackListException;
import com.ruoyi.common.exception.user.CaptchaException;
import com.ruoyi.common.exception.user.UserBlockedException;
import com.ruoyi.common.exception.user.UserDeleteException;
import com.ruoyi.common.exception.user.UserNotExistsException;
import com.ruoyi.common.exception.user.UserPasswordNotMatchException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.IpUtils;
import com.ruoyi.common.utils.MessageUtils;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.UserUtils;
import com.ruoyi.system.service.ISysConfigService;
import com.ruoyi.system.service.ISysMenuService;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * 登录校验方法
 *
 * @author ruoyi
 */
@Component
public class SysLoginService {
    @Autowired
    private SysPasswordService passwordService;

    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private ISysConfigService configService;

    /**
     * 登录
     */
    public SysUser login(String username, String password) {
        // 验证码校验
        if (ShiroConstants.CAPTCHA_ERROR.equals(ServletUtils.getRequest().getAttribute(ShiroConstants.CURRENT_CAPTCHA))) {
            LoginInfo loginInfo = LoginInfo.builder().username(username).status(Constants.LOGIN_FAIL)
                    .message(MessageUtils.message("user.jcaptcha.error")).build();
            BizEventDispatcher.dispatch(BizTopicType.USER_LOGIN, loginInfo);
            throw new CaptchaException();
        }
        // 用户名或密码为空 错误
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            LoginInfo loginInfo = LoginInfo.builder().username(username).status(Constants.LOGIN_FAIL)
                    .message(MessageUtils.message("not.null")).build();
            BizEventDispatcher.dispatch(BizTopicType.USER_LOGIN, loginInfo);
            throw new UserNotExistsException();
        }
        // 密码如果不在指定范围内 错误
        if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH) {
            LoginInfo loginInfo = LoginInfo.builder().username(username).status(Constants.LOGIN_FAIL)
                    .message(MessageUtils.message("user.password.not.match")).build();
            BizEventDispatcher.dispatch(BizTopicType.USER_LOGIN, loginInfo);
            throw new UserPasswordNotMatchException();
        }

        // 用户名不在指定范围内 错误
        if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH) {
            LoginInfo loginInfo = LoginInfo.builder().username(username).status(Constants.LOGIN_FAIL)
                    .message(MessageUtils.message("user.password.not.match")).build();
            BizEventDispatcher.dispatch(BizTopicType.USER_LOGIN, loginInfo);
            throw new UserPasswordNotMatchException();
        }

        // IP黑名单校验
        String blackStr = configService.selectConfigByKey("sys.login.blackIPList");
        if (IpUtils.isMatchedIp(blackStr, UserUtils.getIp())) {
            LoginInfo loginInfo = LoginInfo.builder().username(username).status(Constants.LOGIN_FAIL)
                    .message(MessageUtils.message("login.blocked")).build();
            BizEventDispatcher.dispatch(BizTopicType.USER_LOGIN, loginInfo);
            throw new BlackListException();
        }

        // 查询用户信息
        SysUser user = userService.selectUserByLoginName(username);

        /**
         if (user == null && maybeMobilePhoneNumber(username))
         {
         user = userService.selectUserByPhoneNumber(username);
         }

         if (user == null && maybeEmail(username))
         {
         user = userService.selectUserByEmail(username);
         }
         */

        if (user == null) {
            LoginInfo loginInfo = LoginInfo.builder().username(username).status(Constants.LOGIN_FAIL)
                    .message(MessageUtils.message("user.not.exists")).build();
            BizEventDispatcher.dispatch(BizTopicType.USER_LOGIN, loginInfo);
            throw new UserNotExistsException();
        }

        if (UserStatus.DELETED.getCode().equals(user.getDelFlag())) {
            LoginInfo loginInfo = LoginInfo.builder().username(username).status(Constants.LOGIN_FAIL)
                    .message(MessageUtils.message("user.password.delete")).build();
            BizEventDispatcher.dispatch(BizTopicType.USER_LOGIN, loginInfo);
            throw new UserDeleteException();
        }

        if (UserStatus.DISABLE.getCode().equals(user.getStatus())) {
            LoginInfo loginInfo = LoginInfo.builder().username(username).status(Constants.LOGIN_FAIL)
                    .message(MessageUtils.message("user.blocked")).build();
            BizEventDispatcher.dispatch(BizTopicType.USER_LOGIN, loginInfo);
            throw new UserBlockedException();
        }

        passwordService.validate(user, password);

        LoginInfo loginInfo = LoginInfo.builder().username(username).status(Constants.LOGIN_SUCCESS)
                .message(MessageUtils.message("user.login.success")).build();
        BizEventDispatcher.dispatch(BizTopicType.USER_LOGIN, loginInfo);
        setRolePermission(user);

        recordLoginInfo(user.getUserId());
        return user;
    }

    /**
     private boolean maybeEmail(String username)
     {
     if (!username.matches(UserConstants.EMAIL_PATTERN))
     {
     return false;
     }
     return true;
     }

     private boolean maybeMobilePhoneNumber(String username)
     {
     if (!username.matches(UserConstants.MOBILE_PHONE_NUMBER_PATTERN))
     {
     return false;
     }
     return true;
     }
     */

    /**
     * 设置角色权限
     *
     * @param user 用户信息
     */
    public void setRolePermission(SysUser user) {
        List<SysRole> roles = user.getRoles();
        if (!roles.isEmpty()) {
            // 设置permissions属性，以便数据权限匹配权限
            for (SysRole role : roles) {
                if (StringUtils.equals(role.getStatus(), UserConstants.ROLE_NORMAL) && !role.isAdmin()) {
                    Set<String> rolePerms = menuService.selectPermsByRoleId(role.getRoleId());
                    role.setPermissions(rolePerms);
                }
            }
        }
    }

    /**
     * 记录登录信息
     *
     * @param userId 用户ID
     */
    public void recordLoginInfo(Long userId) {
        SysUser user = new SysUser();
        user.setUserId(userId);
        user.setLoginIp(UserUtils.getIp());
        user.setLoginDate(DateUtils.getNowDate());
        userService.updateUserInfo(user);
    }
}
