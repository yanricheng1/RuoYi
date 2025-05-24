package com.ruoyi.web.controller.workflow;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.workflow.domain.SeProcessInstance;
import com.ruoyi.workflow.service.ISeProcessInstanceService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * 流程实例Controller
 *
 * @author ruoyi
 * @date 2025-05-22
 */
@Controller
@RequestMapping("/workflow/processInstance")
public class SeProcessInstanceController extends BaseController {
    private final String prefix = "workflow/processInstance";

    @Autowired
    private ISeProcessInstanceService seProcessInstanceService;

    @RequiresPermissions("workflow:processInstance:view")
    @GetMapping()
    public String instance() {
        return prefix + "/processInstance";
    }

    /**
     * 查询流程实例列表
     */
    @RequiresPermissions("workflow:processInstance:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SeProcessInstance seProcessInstance) {
        startPage();
        List<SeProcessInstance> list = seProcessInstanceService.selectSeProcessInstanceList(seProcessInstance);
        return getDataTable(list);
    }

    /**
     * 导出流程实例列表
     */
    @RequiresPermissions("workflow:processInstance:export")
    @Log(title = "流程实例", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SeProcessInstance seProcessInstance) {
        List<SeProcessInstance> list = seProcessInstanceService.selectSeProcessInstanceList(seProcessInstance);
        ExcelUtil<SeProcessInstance> util = new ExcelUtil<SeProcessInstance>(SeProcessInstance.class);
        return util.exportExcel(list, "流程实例数据");
    }

    /**
     * 新增流程实例
     */
    @RequiresPermissions("workflow:processInstance:add")
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存流程实例
     */
    @RequiresPermissions("workflow:processInstance:add")
    @Log(title = "流程实例", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SeProcessInstance seProcessInstance) {
        return toAjax(seProcessInstanceService.insertSeProcessInstance(seProcessInstance));
    }

    /**
     * 修改流程实例
     */
    @RequiresPermissions("workflow:processInstance:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap) {
        SeProcessInstance seProcessInstance = seProcessInstanceService.selectSeProcessInstanceById(id);
        mmap.put("seProcessInstance", seProcessInstance);
        return prefix + "/edit";
    }

    /**
     * 修改保存流程实例
     */
    @RequiresPermissions("workflow:processInstance:edit")
    @Log(title = "流程实例", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SeProcessInstance seProcessInstance) {
        return toAjax(seProcessInstanceService.updateSeProcessInstance(seProcessInstance));
    }

    /**
     * 删除流程实例
     */
    @RequiresPermissions("workflow:processInstance:remove")
    @Log(title = "流程实例", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(seProcessInstanceService.deleteSeProcessInstanceByIds(ids));
    }
}
