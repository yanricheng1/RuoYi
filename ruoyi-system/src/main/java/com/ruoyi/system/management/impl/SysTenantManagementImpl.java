package com.ruoyi.system.management.impl;

import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.tenant.utils.CollectionUtils;
import com.ruoyi.system.domain.SysCompany;
import com.ruoyi.system.domain.SysTenant;
import com.ruoyi.system.enums.RoleKey;
import com.ruoyi.system.enums.RoleType;
import com.ruoyi.system.enums.UserBizType;
import com.ruoyi.system.management.SysTenantManagement;
import com.ruoyi.system.service.ISysCompanyService;
import com.ruoyi.system.service.ISysDeptService;
import com.ruoyi.system.service.ISysMenuService;
import com.ruoyi.system.service.ISysRoleService;
import com.ruoyi.system.service.ISysTenantService;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;


@Component
public class SysTenantManagementImpl implements SysTenantManagement {

    @Autowired
    private ISysTenantService sysTenantService;
    @Resource
    private ISysCompanyService sysCompanyService;
    @Resource
    private ISysDeptService deptService;
    @Resource
    private ISysUserService userService;
    @Resource
    private ISysMenuService menuService;
    @Resource

    private ISysRoleService roleService;

    public static void main(String[] args) {
        // 创建一个有向图来表示社交网络
        MutableGraph<String> network = GraphBuilder.directed().build();

        // 添加节点和边
        network.putEdge("A", "B");
        network.putEdge("B", "C");
        network.putEdge("C", "D");
        network.putEdge("D", "A");

        // 查找从Alice到Dave的最短路径
        String start = "A";
        String end = "D";
        List<String> path = findShortestPath(network, start, end);

        // 输出路径
        System.out.println("Shortest path from " + start + " to " + end + ": " + path);
    }


//    private SysMenu getParentMenu(Map<Long, List<SysMenu>> menuListMap, SysMenu rootMenu,String ) {
//        if (sysMenu == null || sysMenu.getParentId() == null || sysMenu.getParentId() == 0) {
//            return null;
//        }
//        sysMenu = menuMap.get(sysMenu.getParentId());
//
//        if (sysMenu == null) {
//            return sysMenu;
//        }
//
//        return getParentMenu(menuMap, sysMenu);
//    }

    // 使用BFS实现查找最短路径的方法
    private static List<String> findShortestPath(MutableGraph<String> graph, String start, String end) {
        // 存储节点到其父节点的映射，用于重建路径
        Map<String, String> parentMap = new HashMap<>();
        // BFS队列
        Queue<String> queue = new LinkedList<>();
        // 添加起点
        queue.add(start);
        // 标记已访问节点
        Set<String> visited = new HashSet<>();
        visited.add(start);

        while (!queue.isEmpty()) {
            String current = queue.poll();

            // 如果找到目标节点，重建并返回路径
            if (current.equals(end)) {
                return reconstructPath(parentMap, end);
            }

            // 遍历当前节点的所有邻居
            for (String neighbor : graph.successors(current)) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                    parentMap.put(neighbor, current);
                }
            }
        }

        // 如果没有找到路径，返回空列表
        return Collections.emptyList();
    }

    // 根据父节点映射重建路径
    private static List<String> reconstructPath(Map<String, String> parentMap, String end) {
        LinkedList<String> path = new LinkedList<>();
        String current = end;
        while (current != null) {
            path.addFirst(current);
            current = parentMap.get(current);
        }
        return path;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertSysTenant(SysTenant tenant, SysUser user) {
        //创建租户
        int result = sysTenantService.insertSysTenant(tenant);

        //创建企业
        SysCompany company = new SysCompany();
        company.setCode(tenant.getCode());
        company.setName(tenant.getName());
        company.setTenantId(String.valueOf(tenant.getId()));
        company.setFullName(tenant.getName());
        company.setTaxNo(tenant.getTaxNo());
        sysCompanyService.insertSysCompany(company);

        //创建部门
        SysDept dept = new SysDept();
        dept.setDeptName(company.getName());
        dept.setCompanyId(String.valueOf(company.getId()));
        dept.setTenantId(company.getTenantId());
        dept.setParentId(0L);
        deptService.insertDept(dept);

        //修改用户信息
        SysUser modifyUser = new SysUser();
        modifyUser.setUserId(user.getUserId());
        modifyUser.setCompanyId(String.valueOf(company.getId()));
        modifyUser.setTenantId(company.getTenantId());
        modifyUser.setBizType(UserBizType.new_user.name());
        user.setCompanyId(String.valueOf(company.getId()));
        user.setTenantId(company.getTenantId());
        modifyUser.setDeptId(dept.getDeptId());
        userService.updateUserInfo(modifyUser);

        SysRole role = new SysRole();
        role.setRoleKey(RoleKey.tenant_admin.name());
        role.setType(RoleType.sys.name());
        role.setStatus("0");
        List<SysRole> roleList = roleService.selectPureRoleList(role);
        if (!CollectionUtils.isEmpty(roleList)) {
            modifyUser.setRoleId(roleList.get(0).getRoleId());
            userService.insertUserRole(user.getUserId(), new Long[]{roleList.get(0).getRoleId()});
        }

        // 拷贝菜单
//        SysMenu menuQuery = new SysMenu();
//        menuQuery.setCompanyId("-1");
//        menuQuery.setTenantId("-1");
//        menuQuery.setBizType(MenuBizType.company.name());
//        List<SysMenu> companyMenuList = menuService.selectCompanyMenuList(menuQuery);
//        Map<Long, SysMenu> menuMap = companyMenuList.stream().collect(Collectors.toMap(SysMenu::getMenuId, sysMenu -> sysMenu, (v1, v2) -> v1));
//        Map<Long, SysMenu> rootMenuMap = companyMenuList.stream().filter(sysMenu -> sysMenu.getParentId()==null || sysMenu.getParentId() == 0)
//                .collect(Collectors.toMap(SysMenu::getMenuId, sysMenu -> sysMenu, (v1, v2) -> v1));
//        Map<Long,List<SysMenu>> menuListMap =  new HashMap<>();
//        rootMenuMap.keySet().forEach(parentId ->  menuListMap.put(parentId, new LinkedList<>()));

//        Map<Long, Long> parentIdMap = new HashMap<>();
//        for (SysMenu sysMenu : companyMenuList) {
//            if(sysMenu.getParentId()==null){
//                continue;
//            }
//            List<SysMenu> menuList = menuListMap.get(sysMenu.getParentId());
//            if(menuList==null){
//                menuList = new ArrayList<>();
//                menuList.add(sysMenu);
//                menuListMap.put(sysMenu.getParentId(), new LinkedList<>());
//            }else{
//                menuList.add(sysMenu);
//            }
//
//
//            SysMenu menu = getParentMenu(menuMap, sysMenu);
//            if (menu != null) {
//                long originId = menu.getMenuId();
//                menu.setCompanyId(String.valueOf(sysCompany.getId()));
//                menu.setTenantId(sysCompany.getTenantId());
//                menu.setBizType(MenuBizType.company.name());
//                menu.setMenuId(null);
//                menuService.insertMenu(menu);
//                parentIdMap.put(originId, menu.getMenuId());
//            }
//        }
        return result;
    }


}
