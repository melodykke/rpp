package com.wrd.rpp.shiro.bean;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class RoleInfo {
    @Id
    @GeneratedValue
    private Integer roleId;
    private String mark;
    private String description;
    private Boolean available;
    @ManyToMany
    private List<UserInfo> userInfoList;
    @ManyToMany(mappedBy = "roleInfoList", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<PermissionInfo> permissionInfoList;
}
