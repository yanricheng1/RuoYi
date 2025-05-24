package com.ruoyi.workflow.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 流程定义部署实例对象 se_deployment_instance
 * 
 * @author ruoyi
 * @date 2025-05-22
 */
public class SeDeploymentInstance extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** PK */
    private String id;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date gmtCreate;

    /** 修改时间 */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date gmtModified;

    /** 流程定义id */
    @Excel(name = "流程定义id")
    private String processDefinitionId;

    /** 流程定义版本 */
    @Excel(name = "流程定义版本")
    private String processDefinitionVersion;

    /** 业务类型 */
    @Excel(name = "业务类型")
    private String processDefinitionType;

    /** 编码 */
    @Excel(name = "编码")
    private String processDefinitionCode;

    /** 名称 */
    @Excel(name = "名称")
    private String processDefinitionName;

    /** 描述 */
    @Excel(name = "描述")
    private String processDefinitionDesc;

    /** 内容 */
    @Excel(name = "内容")
    private String processDefinitionContent;

    /** 部署人 */
    @Excel(name = "部署人")
    private String deploymentUserId;

    /** 张提 */
    @Excel(name = "张提")
    private String deploymentStatus;

    /** 状态 */
    @Excel(name = "状态")
    private String logicStatus;

    /** 企业id */
    @Excel(name = "企业id")
    private String tenantId;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }

    public void setGmtCreate(Date gmtCreate) 
    {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtCreate() 
    {
        return gmtCreate;
    }

    public void setGmtModified(Date gmtModified) 
    {
        this.gmtModified = gmtModified;
    }

    public Date getGmtModified() 
    {
        return gmtModified;
    }

    public void setProcessDefinitionId(String processDefinitionId) 
    {
        this.processDefinitionId = processDefinitionId;
    }

    public String getProcessDefinitionId() 
    {
        return processDefinitionId;
    }

    public void setProcessDefinitionVersion(String processDefinitionVersion) 
    {
        this.processDefinitionVersion = processDefinitionVersion;
    }

    public String getProcessDefinitionVersion() 
    {
        return processDefinitionVersion;
    }

    public void setProcessDefinitionType(String processDefinitionType) 
    {
        this.processDefinitionType = processDefinitionType;
    }

    public String getProcessDefinitionType() 
    {
        return processDefinitionType;
    }

    public void setProcessDefinitionCode(String processDefinitionCode) 
    {
        this.processDefinitionCode = processDefinitionCode;
    }

    public String getProcessDefinitionCode() 
    {
        return processDefinitionCode;
    }

    public void setProcessDefinitionName(String processDefinitionName) 
    {
        this.processDefinitionName = processDefinitionName;
    }

    public String getProcessDefinitionName() 
    {
        return processDefinitionName;
    }

    public void setProcessDefinitionDesc(String processDefinitionDesc) 
    {
        this.processDefinitionDesc = processDefinitionDesc;
    }

    public String getProcessDefinitionDesc() 
    {
        return processDefinitionDesc;
    }

    public void setProcessDefinitionContent(String processDefinitionContent) 
    {
        this.processDefinitionContent = processDefinitionContent;
    }

    public String getProcessDefinitionContent() 
    {
        return processDefinitionContent;
    }

    public void setDeploymentUserId(String deploymentUserId) 
    {
        this.deploymentUserId = deploymentUserId;
    }

    public String getDeploymentUserId() 
    {
        return deploymentUserId;
    }

    public void setDeploymentStatus(String deploymentStatus) 
    {
        this.deploymentStatus = deploymentStatus;
    }

    public String getDeploymentStatus() 
    {
        return deploymentStatus;
    }

    public void setLogicStatus(String logicStatus) 
    {
        this.logicStatus = logicStatus;
    }

    public String getLogicStatus() 
    {
        return logicStatus;
    }

    public void setTenantId(String tenantId) 
    {
        this.tenantId = tenantId;
    }

    public String getTenantId() 
    {
        return tenantId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("gmtCreate", getGmtCreate())
            .append("gmtModified", getGmtModified())
            .append("processDefinitionId", getProcessDefinitionId())
            .append("processDefinitionVersion", getProcessDefinitionVersion())
            .append("processDefinitionType", getProcessDefinitionType())
            .append("processDefinitionCode", getProcessDefinitionCode())
            .append("processDefinitionName", getProcessDefinitionName())
            .append("processDefinitionDesc", getProcessDefinitionDesc())
            .append("processDefinitionContent", getProcessDefinitionContent())
            .append("deploymentUserId", getDeploymentUserId())
            .append("deploymentStatus", getDeploymentStatus())
            .append("logicStatus", getLogicStatus())
            .append("tenantId", getTenantId())
            .toString();
    }
}
