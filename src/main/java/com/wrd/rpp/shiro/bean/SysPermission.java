package com.wrd.rpp.shiro.bean;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class SysPermission {
    @Id
    @GeneratedValue
    private Integer permissionId;
    private String name;
    @Column(columnDefinition="enum('menu', 'button')")
    private String resourceType;//资源类型，[menu|button]
    private String url;//资源路径.
    private String permission;//权限字符串,menu例子：role:*，button例子：role:create,role:update,role:delete,role:view
    private Integer parentId;//父编号
    private String parentIds;//父编号列表
    private Boolean available = Boolean.FALSE;
    @ManyToMany
    @JoinTable(name="sys_role_permission",joinColumns={@JoinColumn(name="permissionId")},inverseJoinColumns={@JoinColumn(name="roleId")})
    private List<SysRole> sysRoleList;
}
