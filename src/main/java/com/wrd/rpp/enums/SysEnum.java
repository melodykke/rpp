package com.wrd.rpp.enums;

import lombok.Getter;

@Getter
public enum SysEnum {
    EMPTY_EXCEL(10, "内容错误，指定EXCEL无内容！"),
    IO_EXCEL(11, "IO错误，未能有效读取指定EXCEL！"),
    CONVERT_ERROR(20, "属性转换错误！"),
    DATA_STORE_ERROR(30, "数据存储错误！"),
    PARAM_ERROR(40, "参数错误！"),
    OPERATION_SUCCESS(1, "操作正常！"),

    DATA_CALLBACK_SUCCESS(100, "数据成功返回"),
    DATA_CALLBACK_FAILED(101, "数据返回错误"),

    REGISTRY_PARAM_ERROR(200, "账号注册错误"),
    REGISTRY_DUPLICATED_ACCOUNTNAME(201, "所注册账号名称已被他人使用，请换个账号名重试"),
    REGISTRY_INCONSISTENT_PASSWORD(202, "输入的账号密码不正确，请确认后重试！"),
    SIGNIN_PARAM_ERROR(210, "账号登录错误")
    ;

    private Integer code;
    private String msg;

    SysEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
