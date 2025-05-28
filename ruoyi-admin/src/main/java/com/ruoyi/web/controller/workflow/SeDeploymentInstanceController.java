package com.ruoyi.web.controller.workflow;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.workflow.domain.SeDeploymentInstance;
import com.ruoyi.workflow.service.ISeDeploymentInstanceService;
import com.ruoyi.common.annotation.RequiresPermissions;
import com.ruoyi.common.annotation.RequiresRoles;
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
 * 流程定义部署实例Controller
 * 
 * @author ruoyi
 * @date 2025-05-22
 */
@Controller
@RequestMapping("/workflow/deploymentInstance")
public class SeDeploymentInstanceController extends BaseController
{
    private String prefix = "workflow/deploymentInstance";

    @Autowired
    private ISeDeploymentInstanceService seDeploymentInstanceService;

    @RequiresPermissions("workflow:deploymentInstance:view")
    @GetMapping()
    public String deploymentInstance()
    {
        return prefix + "/deploymentInstance";
    }

    /**
     * 查询流程定义部署实例列表
     */
    @RequiresPermissions("workflow:deploymentInstance:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SeDeploymentInstance seDeploymentInstance)
    {
        startPage();
        List<SeDeploymentInstance> list = seDeploymentInstanceService.selectSeDeploymentInstanceList(seDeploymentInstance);
        return getDataTable(list);
    }

    /**
     * 导出流程定义部署实例列表
     */
    @RequiresPermissions("workflow:deploymentInstance:export")
    @Log(title = "流程定义部署实例", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SeDeploymentInstance seDeploymentInstance)
    {
        List<SeDeploymentInstance> list = seDeploymentInstanceService.selectSeDeploymentInstanceList(seDeploymentInstance);
        ExcelUtil<SeDeploymentInstance> util = new ExcelUtil<SeDeploymentInstance>(SeDeploymentInstance.class);
        return util.exportExcel(list, "流程定义部署实例数据");
    }

    /**
     * 新增流程定义部署实例
     */
    @RequiresPermissions("workflow:deploymentInstance:add")
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存流程定义部署实例
     */
    @RequiresPermissions("workflow:deploymentInstance:add")
    @Log(title = "流程定义部署实例", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SeDeploymentInstance seDeploymentInstance)
    {
        return toAjax(seDeploymentInstanceService.insertSeDeploymentInstance(seDeploymentInstance));
    }

    /**
     * 修改流程定义部署实例
     */
    @RequiresPermissions("workflow:deploymentInstance:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        SeDeploymentInstance seDeploymentInstance = seDeploymentInstanceService.selectSeDeploymentInstanceById(id);
        mmap.put("seDeploymentInstance", seDeploymentInstance);
        return prefix + "/edit";
    }

    /**
     * 修改保存流程定义部署实例
     */
    @RequiresPermissions("workflow:deploymentInstance:edit")
    @Log(title = "流程定义部署实例", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SeDeploymentInstance seDeploymentInstance)
    {
        return toAjax(seDeploymentInstanceService.updateSeDeploymentInstance(seDeploymentInstance));
    }

    /**
     * 删除流程定义部署实例
     */
    @RequiresPermissions("workflow:deploymentInstance:remove")
    @Log(title = "流程定义部署实例", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(seDeploymentInstanceService.deleteSeDeploymentInstanceByIds(ids));
    }
}
