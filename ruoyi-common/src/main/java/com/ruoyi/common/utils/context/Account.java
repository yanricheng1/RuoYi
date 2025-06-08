package com.ruoyi.common.utils.context;


import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.domain.entity.SysUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Account implements Serializable {
    private static final long serialVersionUID = 2982792257697596989L;
    private Long userId;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 部门父ID
     */
    private Long parentId;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 登录名称
     */
    private String loginName;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户类型
     */
    private String userType;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private String phonenumber;

    /**
     * 用户性别
     */
    private String sex;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 账号状态（0正常 1停用）
     */
    private String status;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private String delFlag;

    private String loginIp;

    private Date loginDate;

    /**
     * 密码最后更新时间
     */
    private Date pwdUpdateDate;

    private SysDept dept;

    private List<SysRole> roles;

    /**
     * 角色组
     */
    private Long[] roleIds;

    /**
     * 岗位组
     */
    private Long[] postIds;

    private Date createTime;


    /**
     * 密码
     */
    private String password;

    /**
     * 盐加密
     */
    private String salt;

    private String tenantId;
    private String companyId;
    private String bizType;
    private String bizId;

    public static Account from(SysUser user) {
        if (user == null) {
            return null;
        }
        Account account = Account.builder()
                .userId(user.getUserId())
                .deptId(user.getDeptId())
                .parentId(user.getParentId())
                .roleId(user.getRoleId())
                .loginName(user.getLoginName())
                .userName(user.getUserName())
                .userType(user.getUserType())
                .email(user.getEmail())
                .phonenumber(user.getPhonenumber())
                .sex(user.getSex())
                .avatar(user.getAvatar())
                .status(user.getStatus())
                .delFlag(user.getDelFlag())
                .loginIp(user.getLoginIp())
                .loginDate(user.getLoginDate())
                .pwdUpdateDate(user.getPwdUpdateDate())
                .dept(user.getDept())
                .roles(user.getRoles())
                .roleIds(user.getRoleIds())
                .postIds(user.getPostIds())
                .createTime(user.getCreateTime())
                .password(user.getPassword())
                .salt(user.getSalt())
                .companyId(user.getCompanyId())
                .tenantId(user.getTenantId())
                .bizId(user.getBizId())
                .bizType(user.getBizType())
                .build();

        return account;
    }

    public SysUser toSysUser() {
        SysUser user = new SysUser();
        user.setUserId(this.getUserId());
        user.setDeptId(this.getDeptId());
        user.setParentId(this.getParentId());
        user.setRoleId(this.getRoleId());
        user.setLoginName(this.getLoginName());
        user.setUserName(this.getUserName());
        user.setUserType(this.getUserType());
        user.setEmail(this.getEmail());
        user.setPhonenumber(this.getPhonenumber());
        user.setSex(this.getSex());
        user.setAvatar(this.getAvatar());
        user.setStatus(this.getStatus());
        user.setDelFlag(this.getDelFlag());
        user.setLoginIp(this.getLoginIp());
        user.setLoginDate(this.getLoginDate());
        user.setPwdUpdateDate(this.getPwdUpdateDate());
        user.setDept(this.getDept());
        user.setRoles(this.getRoles());
        user.setRoleIds(this.getRoleIds());
        user.setPostIds(this.getPostIds());
        user.setCreateTime(this.getCreateTime());
        user.setSalt(this.getSalt());
        user.setPassword(this.getPassword());
        user.setCompanyId(this.getCompanyId());
        user.setTenantId(this.getTenantId());
        user.setBizId(this.getBizId());
        user.setBizType(this.getBizType());
        return user;

    }
}
