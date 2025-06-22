package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.utils.context.AppThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysCompanyMapper;
import com.ruoyi.system.domain.SysCompany;
import com.ruoyi.system.service.ISysCompanyService;
import com.ruoyi.common.core.text.Convert;

/**
 * 企业信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-06-01
 */
@Service
public class SysCompanyServiceImpl implements ISysCompanyService 
{
    @Autowired
    private SysCompanyMapper sysCompanyMapper;

    /**
     * 查询企业信息
     * 
     * @param id 企业信息主键
     * @return 企业信息
     */
    @Override
    public SysCompany selectSysCompanyById(Long id)
    {
        return sysCompanyMapper.selectSysCompanyById(id);
    }

    /**
     * 查询企业信息列表
     * 
     * @param sysCompany 企业信息
     * @return 企业信息
     */
    @Override
    public List<SysCompany> selectSysCompanyList(SysCompany sysCompany)
    {
        sysCompany.setTenantId(AppThreadContext.getAccount().getTenantId());
        return sysCompanyMapper.selectSysCompanyList(sysCompany);
    }

    /**
     * 新增企业信息
     * 
     * @param sysCompany 企业信息
     * @return 结果
     */
    @Override
    public int insertSysCompany(SysCompany sysCompany)
    {
        return sysCompanyMapper.insertSysCompany(sysCompany);
    }

    /**
     * 修改企业信息
     * 
     * @param sysCompany 企业信息
     * @return 结果
     */
    @Override
    public int updateSysCompany(SysCompany sysCompany)
    {
        return sysCompanyMapper.updateSysCompany(sysCompany);
    }

    /**
     * 批量删除企业信息
     * 
     * @param ids 需要删除的企业信息主键
     * @return 结果
     */
    @Override
    public int deleteSysCompanyByIds(String ids)
    {
        return sysCompanyMapper.deleteSysCompanyByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除企业信息信息
     * 
     * @param id 企业信息主键
     * @return 结果
     */
    @Override
    public int deleteSysCompanyById(Long id)
    {
        return sysCompanyMapper.deleteSysCompanyById(id);
    }
}
