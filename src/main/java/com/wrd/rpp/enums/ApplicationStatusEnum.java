package com.wrd.rpp.enums;

import lombok.Getter;

@Getter
public enum ApplicationStatusEnum {

    HAVENT_APPROVAL_YET((byte)0, "未审核"),
    APPROVED((byte)1, "已审核"),
    APPROVAL_DECLINED((byte)2, "拒绝");

    private Byte statusCode;
    private String msg;

    ApplicationStatusEnum(byte statusCode, String msg) {
        this.statusCode = statusCode;
        this.msg = msg;
    }
}
