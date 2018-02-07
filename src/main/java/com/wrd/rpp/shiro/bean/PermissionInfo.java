package com.wrd.rpp.shiro.bean;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class PermissionInfo {
    @Id
    @GeneratedValue
    private Integer permissionId;
    private String name;
    @Column(columnDefinition="enum('menu', 'botton')")
    private String resourceType;
    private String url;
    private String permission;
    private Integer parentId;
    private String parentIds;
    private Boolean available = false;
    @ManyToMany
    private List<RoleInfo> roleInfoList;
}
