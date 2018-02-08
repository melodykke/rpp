package com.wrd.rpp.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@Data
public class UserSigninForm {
    @NotNull(message = "账号名必填")
    private String username;
    @NotNull(message = "密码必填")
    private String password;
}
