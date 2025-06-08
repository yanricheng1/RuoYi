package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RequiresPermissions;
import com.ruoyi.web.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.SysCompany;
import com.ruoyi.system.domain.SysTenant;
import com.ruoyi.system.management.SysCompanyManagement;
import com.ruoyi.system.service.ISysCompanyService;
import com.ruoyi.system.service.ISysDeptService;
import com.ruoyi.system.service.ISysTenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 企业信息Controller
 *
 * @author ruoyi
 * @date 2025-06-01
 */
@Controller
@RequestMapping("/system/company")
public class SysCompanyController extends BaseController {
    private final String prefix = "system/company";

    @Autowired
    private ISysCompanyService sysCompanyService;

    @Autowired
    private ISysTenantService sysTenantService;

    @Autowired
    private ISysDeptService deptService;
    @Resource
    private SysCompanyManagement companyManagement;

    @RequiresPermissions("system:company:view")
    @GetMapping()
    public String company() {
        return prefix + "/company";
    }

    /**
     * 查询企业信息列表
     */
    @RequiresPermissions("system:company:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysCompany sysCompany) {
        startPage();
        List<SysCompany> list = sysCompanyService.selectSysCompanyList(sysCompany);
        return getDataTable(list);
    }

    /**
     * 导出企业信息列表
     */
    @RequiresPermissions("system:company:export")
    @Log(title = "企业信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysCompany sysCompany) {
        List<SysCompany> list = sysCompanyService.selectSysCompanyList(sysCompany);
        ExcelUtil<SysCompany> util = new ExcelUtil<SysCompany>(SysCompany.class);
        return util.exportExcel(list, "企业信息数据");
    }

    /**
     * 新增企业信息
     */
    @RequiresPermissions("system:company:add")
    @GetMapping("/add")
    public String add(ModelMap mmap) {
        List<SysTenant> sysTenants = sysTenantService.selectSysTenantList(new SysTenant());
        mmap.put("tenants", sysTenants);
        return prefix + "/add";
    }

    /**
     * 新增保存企业信息
     */
    @RequiresPermissions("system:company:add")
    @Log(title = "企业信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysCompany sysCompany) {
        return toAjax(companyManagement.insertSysCompany(sysCompany));
    }

    /**
     * 修改企业信息
     */
    @RequiresPermissions("system:company:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        SysCompany sysCompany = sysCompanyService.selectSysCompanyById(id);
        List<SysTenant> sysTenants = sysTenantService.selectSysTenantList(new SysTenant());
        mmap.put("tenants", sysTenants);
        mmap.put("sysCompany", sysCompany);
        return prefix + "/edit";
    }

    /**
     * 修改保存企业信息
     */
    @RequiresPermissions("system:company:edit")
    @Log(title = "企业信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysCompany sysCompany) {
        return toAjax(sysCompanyService.updateSysCompany(sysCompany));
    }

    /**
     * 删除企业信息
     */
    @RequiresPermissions("system:company:remove")
    @Log(title = "企业信息", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(sysCompanyService.deleteSysCompanyByIds(ids));
    }
}
