package com.ruoyi.system.management;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.system.domain.SysTenant;

public interface SysTenantManagement {

    int insertSysTenant(SysTenant sysTenant, SysUser user);
}
