package com.ruoyi.workflow.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


import java.util.Date;

/**
 * 流程实例对象 se_process_instance
 *
 * @author ruoyi
 * @date 2025-05-22
 */
public class SeProcessInstance extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * PK
     */
    private String id;

    /**
     * create time
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Excel(name = "create time", width = 30, dateFormat = "yyyy-MM-dd")
    private Date gmtCreate;

    /**
     * modification time
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Excel(name = "modification time", width = 30, dateFormat = "yyyy-MM-dd")
    private Date gmtModified;

    /**
     * process definition id and version
     */
    @Excel(name = "process definition id and version")
    private String processDefinitionIdAndVersion;

    /**
     * process definition type
     */
    @Excel(name = "process definition type")
    private String processDefinitionType;

    /**
     * 1.running 2.completed 3.aborted
     */
    @Excel(name = " 1.running 2.completed 3.aborted")
    private String status;

    /**
     * parent process instance id
     */
    @Excel(name = "parent process instance id")
    private String parentProcessInstanceId;

    /**
     * parent execution instance id
     */
    @Excel(name = "parent execution instance id")
    private String parentExecutionInstanceId;

    /**
     * start user id
     */
    @Excel(name = "start user id")
    private String startUserId;

    /**
     * biz unique id
     */
    @Excel(name = "biz unique id")
    private String bizUniqueId;

    /**
     * reason
     */
    @Excel(name = "reason")
    private String reason;

    /**
     * comment
     */
    @Excel(name = "comment")
    private String comment;

    /**
     * title
     */
    @Excel(name = "title")
    private String title;

    /**
     * tag
     */
    @Excel(name = "tag")
    private String tag;

    /**
     * 企业id
     */
    private String tenantId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getProcessDefinitionIdAndVersion() {
        return processDefinitionIdAndVersion;
    }

    public void setProcessDefinitionIdAndVersion(String processDefinitionIdAndVersion) {
        this.processDefinitionIdAndVersion = processDefinitionIdAndVersion;
    }

    public String getProcessDefinitionType() {
        return processDefinitionType;
    }

    public void setProcessDefinitionType(String processDefinitionType) {
        this.processDefinitionType = processDefinitionType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getParentProcessInstanceId() {
        return parentProcessInstanceId;
    }

    public void setParentProcessInstanceId(String parentProcessInstanceId) {
        this.parentProcessInstanceId = parentProcessInstanceId;
    }

    public String getParentExecutionInstanceId() {
        return parentExecutionInstanceId;
    }

    public void setParentExecutionInstanceId(String parentExecutionInstanceId) {
        this.parentExecutionInstanceId = parentExecutionInstanceId;
    }

    public String getStartUserId() {
        return startUserId;
    }

    public void setStartUserId(String startUserId) {
        this.startUserId = startUserId;
    }

    public String getBizUniqueId() {
        return bizUniqueId;
    }

    public void setBizUniqueId(String bizUniqueId) {
        this.bizUniqueId = bizUniqueId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("gmtCreate", getGmtCreate())
                .append("gmtModified", getGmtModified())
                .append("processDefinitionIdAndVersion", getProcessDefinitionIdAndVersion())
                .append("processDefinitionType", getProcessDefinitionType())
                .append("status", getStatus())
                .append("parentProcessInstanceId", getParentProcessInstanceId())
                .append("parentExecutionInstanceId", getParentExecutionInstanceId())
                .append("startUserId", getStartUserId())
                .append("bizUniqueId", getBizUniqueId())
                .append("reason", getReason())
                .append("comment", getComment())
                .append("title", getTitle())
                .append("tag", getTag())
                .append("tenantId", getTenantId())
                .toString();
    }
}
