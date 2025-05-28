package com.ruoyi.auth.web.sso.manager;

import com.ruoyi.auth.web.authz.AuthenticationToken;
import com.ruoyi.common.core.domain.entity.SysUser;

public interface LoginManager {
    void login(AuthenticationToken token);
    void logout(SysUser user);
}
