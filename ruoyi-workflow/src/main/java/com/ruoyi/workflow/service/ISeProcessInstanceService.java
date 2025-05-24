package com.ruoyi.workflow.service;


import com.ruoyi.workflow.domain.SeProcessInstance;

import java.util.List;

/**
 * 流程实例Service接口
 *
 * @author ruoyi
 * @date 2025-05-22
 */
public interface ISeProcessInstanceService {
    /**
     * 查询流程实例
     *
     * @param id 流程实例主键
     * @return 流程实例
     */
    SeProcessInstance selectSeProcessInstanceById(String id);

    /**
     * 查询流程实例列表
     *
     * @param seProcessInstance 流程实例
     * @return 流程实例集合
     */
    List<SeProcessInstance> selectSeProcessInstanceList(SeProcessInstance seProcessInstance);

    /**
     * 新增流程实例
     *
     * @param seProcessInstance 流程实例
     * @return 结果
     */
    int insertSeProcessInstance(SeProcessInstance seProcessInstance);

    /**
     * 修改流程实例
     *
     * @param seProcessInstance 流程实例
     * @return 结果
     */
    int updateSeProcessInstance(SeProcessInstance seProcessInstance);

    /**
     * 批量删除流程实例
     *
     * @param ids 需要删除的流程实例主键集合
     * @return 结果
     */
    int deleteSeProcessInstanceByIds(String ids);

    /**
     * 删除流程实例信息
     *
     * @param id 流程实例主键
     * @return 结果
     */
    int deleteSeProcessInstanceById(String id);
}
