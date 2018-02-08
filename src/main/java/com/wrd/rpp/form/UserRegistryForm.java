package com.wrd.rpp.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Data
public class UserRegistryForm {
    @NotEmpty(message = "账号名必填")
    private String accountName;
    @NotEmpty(message = "账号密码必填")
    private String accountPassword;
    @NotEmpty(message = "账号昵称必填")
    private String userName;
}
