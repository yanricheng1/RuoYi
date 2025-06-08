package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysCompany;

/**
 * 企业信息Mapper接口
 * 
 * @author ruoyi
 * @date 2025-06-01
 */
public interface SysCompanyMapper 
{
    /**
     * 查询企业信息
     * 
     * @param id 企业信息主键
     * @return 企业信息
     */
    public SysCompany selectSysCompanyById(Long id);

    /**
     * 查询企业信息列表
     * 
     * @param sysCompany 企业信息
     * @return 企业信息集合
     */
    public List<SysCompany> selectSysCompanyList(SysCompany sysCompany);

    /**
     * 新增企业信息
     * 
     * @param sysCompany 企业信息
     * @return 结果
     */
    public int insertSysCompany(SysCompany sysCompany);

    /**
     * 修改企业信息
     * 
     * @param sysCompany 企业信息
     * @return 结果
     */
    public int updateSysCompany(SysCompany sysCompany);

    /**
     * 删除企业信息
     * 
     * @param id 企业信息主键
     * @return 结果
     */
    public int deleteSysCompanyById(Long id);

    /**
     * 批量删除企业信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysCompanyByIds(String[] ids);
}
