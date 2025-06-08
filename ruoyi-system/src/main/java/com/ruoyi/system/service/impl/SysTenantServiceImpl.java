package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysTenantMapper;
import com.ruoyi.system.domain.SysTenant;
import com.ruoyi.system.service.ISysTenantService;
import com.ruoyi.common.core.text.Convert;

/**
 * 租户信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-06-01
 */
@Service
public class SysTenantServiceImpl implements ISysTenantService 
{
    @Autowired
    private SysTenantMapper sysTenantMapper;

    /**
     * 查询租户信息
     * 
     * @param id 租户信息主键
     * @return 租户信息
     */
    @Override
    public SysTenant selectSysTenantById(Long id)
    {
        return sysTenantMapper.selectSysTenantById(id);
    }

    /**
     * 查询租户信息列表
     * 
     * @param sysTenant 租户信息
     * @return 租户信息
     */
    @Override
    public List<SysTenant> selectSysTenantList(SysTenant sysTenant)
    {
        return sysTenantMapper.selectSysTenantList(sysTenant);
    }

    /**
     * 新增租户信息
     * 
     * @param sysTenant 租户信息
     * @return 结果
     */
    @Override
    public int insertSysTenant(SysTenant sysTenant)
    {
        return sysTenantMapper.insertSysTenant(sysTenant);
    }

    /**
     * 修改租户信息
     * 
     * @param sysTenant 租户信息
     * @return 结果
     */
    @Override
    public int updateSysTenant(SysTenant sysTenant)
    {
        return sysTenantMapper.updateSysTenant(sysTenant);
    }

    /**
     * 批量删除租户信息
     * 
     * @param ids 需要删除的租户信息主键
     * @return 结果
     */
    @Override
    public int deleteSysTenantByIds(String ids)
    {
        return sysTenantMapper.deleteSysTenantByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除租户信息信息
     * 
     * @param id 租户信息主键
     * @return 结果
     */
    @Override
    public int deleteSysTenantById(Long id)
    {
        return sysTenantMapper.deleteSysTenantById(id);
    }
}
