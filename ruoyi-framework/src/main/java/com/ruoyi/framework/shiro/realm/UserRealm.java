package com.ruoyi.framework.shiro.realm;

import com.ruoyi.auth.web.authc.AuthenticationInfo;
import com.ruoyi.auth.web.authc.authentication.SimpleAuthenticationInfo;
import com.ruoyi.auth.web.authc.exception.AuthenticationException;
import com.ruoyi.auth.web.authc.exception.ExcessiveAttemptsException;
import com.ruoyi.auth.web.authc.exception.IncorrectCredentialsException;
import com.ruoyi.auth.web.authc.exception.LockedAccountException;
import com.ruoyi.auth.web.authc.exception.UnknownAccountException;
import com.ruoyi.auth.web.authz.AuthenticationToken;
import com.ruoyi.auth.web.authz.AuthorizationInfo;
import com.ruoyi.auth.web.authz.UsernamePasswordToken;
import com.ruoyi.auth.web.authz.authorization.SimpleAuthorizationInfo;
import com.ruoyi.auth.web.principal.PrincipalCollection;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.exception.user.CaptchaException;
import com.ruoyi.common.exception.user.RoleBlockedException;
import com.ruoyi.common.exception.user.UserBlockedException;
import com.ruoyi.common.exception.user.UserNotExistsException;
import com.ruoyi.common.exception.user.UserPasswordNotMatchException;
import com.ruoyi.common.exception.user.UserPasswordRetryLimitExceedException;
import com.ruoyi.common.utils.UserUtils;
import com.ruoyi.auth.web.sso.service.SysLoginService;
import com.ruoyi.system.service.ISysMenuService;
import com.ruoyi.system.service.ISysRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * 自定义Realm 处理登录 权限
 *
 * @author ruoyi
 */
public class UserRealm  //extends AuthorizingRealm
{
    private static final Logger log = LoggerFactory.getLogger(UserRealm.class);

    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private SysLoginService loginService;

    /**
     * 授权
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
        SysUser user = UserUtils.getSysUser();
        // 角色列表
        Set<String> roles = new HashSet<String>();
        // 功能列表
        Set<String> menus = new HashSet<String>();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 管理员拥有所有权限
        if (user.isAdmin()) {
            info.addRole("admin");
            info.addStringPermission("*:*:*");
        } else {
            roles = roleService.selectRoleKeys(user.getUserId());
            menus = menuService.selectPermsByUserId(user.getUserId());
            // 角色加入AuthorizationInfo认证对象
            info.setRoles(roles);
            // 权限加入AuthorizationInfo认证对象
            info.setStringPermissions(menus);
        }
        return info;
    }

}
