package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysTenant;

/**
 * 租户信息Mapper接口
 * 
 * @author ruoyi
 * @date 2025-06-01
 */
public interface SysTenantMapper 
{
    /**
     * 查询租户信息
     * 
     * @param id 租户信息主键
     * @return 租户信息
     */
    public SysTenant selectSysTenantById(Long id);

    /**
     * 查询租户信息列表
     * 
     * @param sysTenant 租户信息
     * @return 租户信息集合
     */
    public List<SysTenant> selectSysTenantList(SysTenant sysTenant);

    /**
     * 新增租户信息
     * 
     * @param sysTenant 租户信息
     * @return 结果
     */
    public int insertSysTenant(SysTenant sysTenant);

    /**
     * 修改租户信息
     * 
     * @param sysTenant 租户信息
     * @return 结果
     */
    public int updateSysTenant(SysTenant sysTenant);

    /**
     * 删除租户信息
     * 
     * @param id 租户信息主键
     * @return 结果
     */
    public int deleteSysTenantById(Long id);

    /**
     * 批量删除租户信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysTenantByIds(String[] ids);
}
