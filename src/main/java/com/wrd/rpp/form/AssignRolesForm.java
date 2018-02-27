package com.wrd.rpp.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class AssignRolesForm {
    @NotEmpty(message = "用户名不能为空")
    private String username;
    @NotEmpty(message = "密码必填")
    private List<String> roles;
}
