package com.wrd.rpp.shiro.bean;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter@Setter
public class UserInfo {
    @Id
    @GeneratedValue
    private Integer userId;
    @Column(unique = true)
    private String username;
    private String regionCode;
    private String email;
    private String phone;
    private String name;
    private String password;
    private String salt;
    private Byte state; //0:创建未认证（比如没有激活，没有输入验证码等等）--等待验证的用户 , 1:正常状态,2：用户被锁定.

    @ManyToOne
    @JoinColumn(name="parent_id")
    private UserInfo parent;
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<UserInfo> children = new HashSet<UserInfo>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "sys_user_role", joinColumns = { @JoinColumn(name = "userId") }, inverseJoinColumns ={@JoinColumn(name = "roleId") })
    private List<SysRole> sysRoleList = new ArrayList<>();

    //为了密码更安全，使用username + salt
    public String fetchUsernameAndSalt() {
        return this.username + this.salt;
    }
}
