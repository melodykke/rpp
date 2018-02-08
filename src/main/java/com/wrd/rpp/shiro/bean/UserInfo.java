package com.wrd.rpp.shiro.bean;

import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class UserInfo {
    @Id
    @GeneratedValue
    private Integer userId;
    private String accountName;
    private String userName;
    private String accountPassword;
    private String accountSalt;
    private byte accountStatus; //0 未激活 1激活 2挂起
    @ManyToMany(mappedBy = "userInfoList", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<RoleInfo> roleInfoList;

    //为了密码更安全，使用username + salt
    public String fetchUsernameAndSalt() {
        return this.accountName + this.accountSalt;
    }
}
