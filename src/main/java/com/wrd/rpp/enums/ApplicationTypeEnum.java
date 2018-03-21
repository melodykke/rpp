package com.wrd.rpp.enums;

import lombok.Getter;

@Getter
public enum ApplicationTypeEnum {
    power_plant_base_info_application("1", "电站基础信息提交申请");
    ;

    private String code;
    private String msg;

    ApplicationTypeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
