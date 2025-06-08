package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 企业信息对象 sys_company
 * 
 * @author ruoyi
 * @date 2025-06-01
 */
public class SysCompany extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 租户id */
    @Excel(name = "租户id")
    private String tenantId;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 编码 */
    @Excel(name = "编码")
    private String code;

    /** 税号 */
    @Excel(name = "税号")
    private String taxNo;

    /** 全称 */
    @Excel(name = "全称")
    private String fullName;

    /** 逻辑删除 */
    @Excel(name = "逻辑删除")
    private Long isDelete;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createDate;

    /** 创建人id */
    @Excel(name = "创建人id")
    private String creatorId;

    /** 创建人名称 */
    @Excel(name = "创建人名称")
    private String creatorName;

    /** 更新人id */
    @Excel(name = "更新人id")
    private String modifierId;

    /** 更新人名称 */
    @Excel(name = "更新人名称")
    private String modifierName;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date modifyDate;

    /** 版本 */
    @Excel(name = "版本")
    private Long version;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setTenantId(String tenantId) 
    {
        this.tenantId = tenantId;
    }

    public String getTenantId() 
    {
        return tenantId;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }

    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }

    public void setTaxNo(String taxNo) 
    {
        this.taxNo = taxNo;
    }

    public String getTaxNo() 
    {
        return taxNo;
    }

    public void setFullName(String fullName) 
    {
        this.fullName = fullName;
    }

    public String getFullName() 
    {
        return fullName;
    }

    public void setIsDelete(Long isDelete) 
    {
        this.isDelete = isDelete;
    }

    public Long getIsDelete() 
    {
        return isDelete;
    }

    public void setCreateDate(Date createDate) 
    {
        this.createDate = createDate;
    }

    public Date getCreateDate() 
    {
        return createDate;
    }

    public void setCreatorId(String creatorId) 
    {
        this.creatorId = creatorId;
    }

    public String getCreatorId() 
    {
        return creatorId;
    }

    public void setCreatorName(String creatorName) 
    {
        this.creatorName = creatorName;
    }

    public String getCreatorName() 
    {
        return creatorName;
    }

    public void setModifierId(String modifierId) 
    {
        this.modifierId = modifierId;
    }

    public String getModifierId() 
    {
        return modifierId;
    }

    public void setModifierName(String modifierName) 
    {
        this.modifierName = modifierName;
    }

    public String getModifierName() 
    {
        return modifierName;
    }

    public void setModifyDate(Date modifyDate) 
    {
        this.modifyDate = modifyDate;
    }

    public Date getModifyDate() 
    {
        return modifyDate;
    }

    public void setVersion(Long version) 
    {
        this.version = version;
    }

    public Long getVersion() 
    {
        return version;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("tenantId", getTenantId())
            .append("name", getName())
            .append("code", getCode())
            .append("taxNo", getTaxNo())
            .append("fullName", getFullName())
            .append("isDelete", getIsDelete())
            .append("createDate", getCreateDate())
            .append("creatorId", getCreatorId())
            .append("creatorName", getCreatorName())
            .append("modifierId", getModifierId())
            .append("modifierName", getModifierName())
            .append("modifyDate", getModifyDate())
            .append("version", getVersion())
            .toString();
    }
}
