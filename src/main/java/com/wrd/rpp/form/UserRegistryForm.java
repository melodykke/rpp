package com.wrd.rpp.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Data
public class UserRegistryForm {
    @NotEmpty(message = "账号名必填")
    private String username;
    @NotEmpty(message = "账号密码必填")
    private String password;
    @NotEmpty(message = "账号昵称必填")
    private String name;
}
