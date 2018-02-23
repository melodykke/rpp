package com.wrd.rpp.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Data
public class UserRegistryForm {
    @NotEmpty(message = "账号名必填")
    private String username;
    @NotEmpty(message = "行政区名必填")
    private String regionName;
    @NotEmpty(message = "上级行政机构名")
    private String superiorRegionName;
    @NotEmpty(message = "真实姓名必填")
    private String name;
    @NotEmpty(message = "账号密码必填")
    private String password;
    @NotEmpty(message = "账号确认密码必填")
    private String rPassword;
    @NotEmpty(message = "联系电话必填")
    private String phone;
    private String email;
}
