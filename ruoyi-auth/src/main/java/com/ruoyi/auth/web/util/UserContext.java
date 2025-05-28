package com.ruoyi.auth.web.util;

import com.ruoyi.common.core.domain.entity.SysUser;

public class UserContext {
    public static String getUsername(){
        return "admin";
    }

    public static SysUser getSysUser(){
        SysUser user = new SysUser();
        user.setUserName("admin");
        return user;
    }

    public static void setSysUser(SysUser user){

    }
}
