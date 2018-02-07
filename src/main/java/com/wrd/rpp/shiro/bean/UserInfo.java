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
    private byte accountStatus;
    @ManyToMany(mappedBy = "userInfoList", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<RoleInfo> roleInfoList;

}
