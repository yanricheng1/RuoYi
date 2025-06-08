package com.ruoyi.system.management.impl;

import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.system.domain.SysCompany;
import com.ruoyi.system.management.SysCompanyManagement;
import com.ruoyi.system.service.ISysCompanyService;
import com.ruoyi.system.service.ISysDeptService;
import com.ruoyi.system.service.ISysMenuService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Component
public class SysCompanyManagementImpl implements SysCompanyManagement {

    @Resource
    private ISysCompanyService sysCompanyService;

    @Resource
    private ISysDeptService deptService;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertSysCompany(SysCompany sysCompany) {
        int result = sysCompanyService.insertSysCompany(sysCompany);
        SysDept dept = new SysDept();
        dept.setDeptName(sysCompany.getName());
        dept.setCompanyId(sysCompany.getId());
        dept.setTenantId(sysCompany.getTenantId());
        deptService.insertDept(dept);
        return result;
    }
}
