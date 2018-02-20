package com.wrd.rpp.shiro.bean;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class SysRole {
    @Id
    @GeneratedValue
    private Integer roleId;
    private String mark;// 角色标识程序中判断使用,如"admin",这个是唯一的:
    private String description;
    private Boolean available = Boolean.FALSE;

    // 用户 - 角色关系定义;
    @ManyToMany
    @JoinTable(name="sys_User_Role",joinColumns={@JoinColumn(name="roleId")},inverseJoinColumns={@JoinColumn(name="userId")})
    private List<UserInfo> userInfoList;

    //角色 -- 权限关系：多对多关系;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name="sys_role_permission",joinColumns={@JoinColumn(name="roleId")},inverseJoinColumns={@JoinColumn(name="permissionId")})
    private List<SysPermission> sysPermissionList;
}
