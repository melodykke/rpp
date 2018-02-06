package com.wrd.rpp.msg;

import com.wrd.rpp.enums.SysEnum;
import lombok.Getter;

@Getter
public class SysMsg {
    private Integer code;
    private String msg;

    public SysMsg(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public SysMsg(SysEnum sysEnum){
        this.code = sysEnum.getCode();
        this.msg = sysEnum.getMsg();
    }
}
