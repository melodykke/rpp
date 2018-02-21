package com.wrd.rpp.shiro.bean;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter@Setter
public class UserInfo {
    @Id
    @GeneratedValue
    private Integer userId;
    @Column(unique = true)
    private String username;
    private String regionName;
    private String email;
    private String phone;
    private String name;
    private String password;
    private String salt;
    private byte state; //0:创建未认证（比如没有激活，没有输入验证码等等）--等待验证的用户 , 1:正常状态,2：用户被锁定.
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "sys_user_role", joinColumns = { @JoinColumn(name = "userId") }, inverseJoinColumns ={@JoinColumn(name = "roleId") })
    private List<SysRole> sysRoleList;

    //为了密码更安全，使用username + salt
    public String fetchUsernameAndSalt() {
        return this.username + this.salt;
    }
}
