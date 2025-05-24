package com.ruoyi.workflow.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.workflow.domain.SeProcessInstance;
import com.ruoyi.workflow.mapper.SeProcessInstanceMapper;
import com.ruoyi.workflow.service.ISeProcessInstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 流程实例Service业务层处理
 *
 * @author ruoyi
 * @date 2025-05-22
 */
@Service
public class SeProcessInstanceServiceImpl implements ISeProcessInstanceService {
    @Autowired
    private SeProcessInstanceMapper seProcessInstanceMapper;

    /**
     * 查询流程实例
     *
     * @param id 流程实例主键
     * @return 流程实例
     */
    @Override
    public SeProcessInstance selectSeProcessInstanceById(String id) {
        return seProcessInstanceMapper.selectSeProcessInstanceById(id);
    }

    /**
     * 查询流程实例列表
     *
     * @param seProcessInstance 流程实例
     * @return 流程实例
     */
    @Override
    public List<SeProcessInstance> selectSeProcessInstanceList(SeProcessInstance seProcessInstance) {
        return seProcessInstanceMapper.selectSeProcessInstanceList(seProcessInstance);
    }

    /**
     * 新增流程实例
     *
     * @param seProcessInstance 流程实例
     * @return 结果
     */
    @Override
    public int insertSeProcessInstance(SeProcessInstance seProcessInstance) {
        if(StringUtils.isBlank(seProcessInstance.getStatus())){
            seProcessInstance.setStatus("init");
        }
        if(StringUtils.isBlank(seProcessInstance.getTenantId())){
            seProcessInstance.setTenantId("-1");
        }
        Date now = new Date();
        seProcessInstance.setGmtCreate(now);
        seProcessInstance.setGmtModified(now);
        return seProcessInstanceMapper.insertSeProcessInstance(seProcessInstance);
    }

    /**
     * 修改流程实例
     *
     * @param seProcessInstance 流程实例
     * @return 结果
     */
    @Override
    public int updateSeProcessInstance(SeProcessInstance seProcessInstance) {
        Date now = new Date();
        seProcessInstance.setGmtModified(now);
        return seProcessInstanceMapper.updateSeProcessInstance(seProcessInstance);
    }

    /**
     * 批量删除流程实例
     *
     * @param ids 需要删除的流程实例主键
     * @return 结果
     */
    @Override
    public int deleteSeProcessInstanceByIds(String ids) {
        return seProcessInstanceMapper.deleteSeProcessInstanceByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除流程实例信息
     *
     * @param id 流程实例主键
     * @return 结果
     */
    @Override
    public int deleteSeProcessInstanceById(String id) {
        return seProcessInstanceMapper.deleteSeProcessInstanceById(id);
    }
}
