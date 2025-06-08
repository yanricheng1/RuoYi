package com.ruoyi.common.utils;


import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.context.Account;
import com.ruoyi.common.utils.context.AppThreadContext;
import com.ruoyi.common.utils.spring.SpringUtils;

/**
 * shiro 工具类
 *
 * @author ruoyi
 */
public class UserUtils {


    public static Account unbindAccount() {
        return AppThreadContext.unbindAccount();
    }

    public static Account getAccount() {
        return AppThreadContext.getAccount();
    }

    public static SysUser getSysUser() {
        return AppThreadContext.getAccount().toSysUser();
    }

    public static Long getUserId() {
        return AppThreadContext.getAccount().getUserId().longValue();
    }

    public static String getLoginName() {
        return AppThreadContext.getAccount().getLoginName();
    }

    public static String getIp() {
        return IpUtils.getIpAddr(ServletUtils.getRequest());
    }

    public static String getSessionId() {
        return "";
    }

}
