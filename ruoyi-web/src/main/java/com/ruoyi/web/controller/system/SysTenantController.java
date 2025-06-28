package com.ruoyi.web.controller.system;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RequiresPermissions;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.SysTenant;
import com.ruoyi.system.management.SysTenantManagement;
import com.ruoyi.system.service.ISysTenantService;
import com.ruoyi.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 租户信息Controller
 *
 * @author ruoyi
 * @date 2025-06-01
 */
@Controller
@RequestMapping("/system/tenant")
public class SysTenantController extends BaseController {
    private final String prefix = "system/tenant";

    @Autowired
    private ISysTenantService sysTenantService;
    @Autowired
    private SysTenantManagement sysTenantManagement;

    @RequiresPermissions("system:tenant:view")
    @GetMapping()
    public String tenant() {
        return prefix + "/tenant";
    }

    @RequiresPermissions("system:tenant:view")
    @GetMapping("/create")
    public String create() {
        return prefix + "/create";
    }


    @RequiresPermissions("system:tenant:view")
    @GetMapping("/detail")
    public String tenantDetail(ModelMap mmap) {
        List<SysTenant> list = sysTenantService.selectSysTenantList(new SysTenant());
        if (list != null && list.size() > 0) {
            mmap.put("sysTenant", list.get(0));
        }

        return prefix + "/detail";
    }

    /**
     * 查询租户信息列表
     */
    @RequiresPermissions("system:tenant:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysTenant sysTenant) {
        startPage();
        SysUser sysUser = getSysUser();
        if (StringUtils.isBlank(sysUser.getTenantId())) {
            return getDataTable(new ArrayList<>());
        }
//        List<SysTenant> list = sysTenantService.selectSysTenantList(sysTenant);
        List<SysTenant> list = new ArrayList<>();
        list.add(sysTenantService.selectSysTenantById(Long.parseLong(sysUser.getTenantId())));
        return getDataTable(list);
    }

    /**
     * 导出租户信息列表
     */
    @RequiresPermissions("system:tenant:export")
    @Log(title = "租户信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysTenant sysTenant) {
        List<SysTenant> list = sysTenantService.selectSysTenantList(sysTenant);
        ExcelUtil<SysTenant> util = new ExcelUtil<SysTenant>(SysTenant.class);
        return util.exportExcel(list, "租户信息数据");
    }

    /**
     * 新增租户信息
     */
    @RequiresPermissions("system:tenant:add")
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存租户信息
     */
    @RequiresPermissions("system:tenant:add")
    @Log(title = "租户信息", businessType = BusinessType.INSERT)
    @RequestMapping(value = { "/add" }, method = { RequestMethod.POST }, produces="application/json;charset=UTF-8")
    @ResponseBody
    public AjaxResult addSave(SysTenant sysTenant) {
        SysUser user = getSysUser();
        int result = sysTenantManagement.insertSysTenant(sysTenant, user);
        reLoadSysUser(getUserId(), user.getCompanyId(), user.getTenantId());
        logger.info("新增租户信息：{}", JSON.toJSONString(getSysUser()));
        return success();
    }

    /**
     * 修改租户信息
     */
    @RequiresPermissions("system:tenant:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        SysTenant sysTenant = sysTenantService.selectSysTenantById(id);
        mmap.put("sysTenant", sysTenant);
        return prefix + "/edit";
    }

    /**
     * 修改保存租户信息
     */
    @RequiresPermissions("system:tenant:edit")
    @Log(title = "租户信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysTenant sysTenant) {
        return toAjax(sysTenantService.updateSysTenant(sysTenant));
    }

    /**
     * 删除租户信息
     */
    @RequiresPermissions("system:tenant:remove")
    @Log(title = "租户信息", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(sysTenantService.deleteSysTenantByIds(ids));
    }
}
