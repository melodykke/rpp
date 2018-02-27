package com.wrd.rpp.enums;

import lombok.Getter;

@Getter
public enum UserAccountStatusEnum {
    ACCOUNT_INACTIVATED((byte)0, "未激活 "),
    ACCOUNT_ACTIVATED((byte)1, "正常"),
    ACCOUNT_SUSPENDED((byte)2, "挂起")
    ;

    private Byte code;
    private String msg;

    UserAccountStatusEnum(Byte code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
