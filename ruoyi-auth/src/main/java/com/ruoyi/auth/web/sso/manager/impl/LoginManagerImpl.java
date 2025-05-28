package com.ruoyi.auth.web.sso.manager.impl;

import com.ruoyi.auth.web.authc.exception.AccountException;
import com.ruoyi.auth.web.authc.exception.AuthenticationException;
import com.ruoyi.auth.web.authc.exception.CredentialsException;
import com.ruoyi.auth.web.authc.exception.DisabledAccountException;
import com.ruoyi.auth.web.authc.exception.ExcessiveAttemptsException;
import com.ruoyi.auth.web.authc.exception.IncorrectCredentialsException;
import com.ruoyi.auth.web.authc.exception.LockedAccountException;
import com.ruoyi.auth.web.authc.exception.UnknownAccountException;
import com.ruoyi.auth.web.authz.AuthenticationToken;
import com.ruoyi.auth.web.authz.UsernamePasswordToken;
import com.ruoyi.auth.web.sso.PasswordEncryptStrategy;
import com.ruoyi.auth.web.sso.manager.LoginManager;
import com.ruoyi.auth.web.sso.service.SysLoginService;
import com.ruoyi.auth.web.sso.strategy.PasswordEncryptorType;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.exception.user.CaptchaException;
import com.ruoyi.common.exception.user.RoleBlockedException;
import com.ruoyi.common.exception.user.UserBlockedException;
import com.ruoyi.common.exception.user.UserNotExistsException;
import com.ruoyi.common.exception.user.UserPasswordNotMatchException;
import com.ruoyi.common.exception.user.UserPasswordRetryLimitExceedException;
import com.ruoyi.common.utils.context.Account;
import com.ruoyi.common.utils.context.AppThreadContext;
import com.ruoyi.system.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component
@Slf4j
public class LoginManagerImpl implements LoginManager {

    @Resource

    ISysUserService userService;
//
//    @Resource
//    private PasswordEncryptStrategy passwordEncryptStrategy;

    @Resource
    private SysLoginService loginService;

    @Override
    public void login(AuthenticationToken token) {
        SysUser user = null;
        if (token instanceof UsernamePasswordToken) {
            UsernamePasswordToken account = (UsernamePasswordToken) token;
            String username = account.getPrincipal().toString();
            String password = new String(account.getPassword());

            try {
                user = loginService.login(username, password);
            } catch (CaptchaException e) {
                throw new AuthenticationException(e.getMessage(), e);
            } catch (UserNotExistsException e) {
                throw new UnknownAccountException(e.getMessage(), e);
            } catch (UserPasswordNotMatchException e) {
                throw new IncorrectCredentialsException(e.getMessage(), e);
            } catch (UserPasswordRetryLimitExceedException e) {
                throw new ExcessiveAttemptsException(e.getMessage(), e);
            } catch (UserBlockedException e) {
                throw new LockedAccountException(e.getMessage(), e);
            } catch (RoleBlockedException e) {
                throw new LockedAccountException(e.getMessage(), e);
            } catch (Exception e) {
                log.info("对用户[" + username + "]进行登录验证..验证未通过{}", e.getMessage());
                throw new AuthenticationException(e.getMessage(), e);
            }

        }


        if (user != null) {
            AppThreadContext.bind(Account.from(user));
        }
    }

    @Override
    public void logout(SysUser user) {

    }
}
