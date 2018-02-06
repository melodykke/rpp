package com.wrd.rpp.enums;

import lombok.Getter;

@Getter
public enum SysEnum {
    EMPTY_EXCEL(10, "内容错误，指定EXCEL无内容！"),
    IO_EXCEL(11, "IO错误，未能有效读取指定EXCEL！"),
    CONVERT_ERROR(20, "属性转换错误！"),
    DATA_STORE_ERROR(30, "数据存储错误！"),
    OPERATION_SUCCESS(1, "操作正常！"),

    DATA_CALLBACK_SUCCESS(100, "数据成功返回"),
    DATA_CALLBACK_FAILED(101, "数据返回错误")
    ;

    private Integer code;
    private String msg;

    SysEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
