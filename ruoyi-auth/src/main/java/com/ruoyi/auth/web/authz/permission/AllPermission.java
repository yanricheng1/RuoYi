package com.ruoyi.auth.web.authz.permission;

import com.ruoyi.auth.web.authz.Permission;

import java.io.Serializable;

public class AllPermission implements Permission, Serializable {

    /**
     * Always returns <tt>true</tt>, indicating any Subject granted this permission can do anything.
     *
     * @param p the Permission to check for implies logic.
     * @return <tt>true</tt> always, indicating any Subject grated this permission can do anything.
     */
    public boolean implies(Permission p) {
        return true;
    }
}