package com.ruoyi.workflow.mapper;



import com.ruoyi.workflow.domain.SeProcessInstance;

import java.util.List;

/**
 * 流程实例Mapper接口
 * 
 * @author ruoyi
 * @date 2025-05-22
 */
public interface SeProcessInstanceMapper 
{
    /**
     * 查询流程实例
     * 
     * @param id 流程实例主键
     * @return 流程实例
     */
    public SeProcessInstance selectSeProcessInstanceById(String id);

    /**
     * 查询流程实例列表
     * 
     * @param seProcessInstance 流程实例
     * @return 流程实例集合
     */
    public List<SeProcessInstance> selectSeProcessInstanceList(SeProcessInstance seProcessInstance);

    /**
     * 新增流程实例
     * 
     * @param seProcessInstance 流程实例
     * @return 结果
     */
    public int insertSeProcessInstance(SeProcessInstance seProcessInstance);

    /**
     * 修改流程实例
     * 
     * @param seProcessInstance 流程实例
     * @return 结果
     */
    public int updateSeProcessInstance(SeProcessInstance seProcessInstance);

    /**
     * 删除流程实例
     * 
     * @param id 流程实例主键
     * @return 结果
     */
    public int deleteSeProcessInstanceById(String id);

    /**
     * 批量删除流程实例
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSeProcessInstanceByIds(String[] ids);
}
