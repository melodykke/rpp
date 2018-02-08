package com.wrd.rpp.enums;

import lombok.Getter;

@Getter
public enum UserAccontStatusEnum {
    ACCOUNT_INACTIVATED((byte)0, "账号未激活 "),
    ACCOUNT_ACTIVATED((byte)1, "账号正常"),
    ACCOUNT_SUSPENDED((byte)2, "账号挂起")
    ;

    private Byte code;
    private String msg;

    UserAccontStatusEnum(Byte code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
