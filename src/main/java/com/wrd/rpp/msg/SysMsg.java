package com.wrd.rpp.msg;

import com.wrd.rpp.enums.SysEnum;
import lombok.Getter;

@Getter
public class SysMsg {
    private Integer code;
    private String msg;
    private Object object;

    public SysMsg(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public SysMsg(Integer code, String msg, Object object) {
        this.code = code;
        this.msg = msg;
        this.object = object;
    }
    public SysMsg(SysEnum sysEnum){
        this.code = sysEnum.getCode();
        this.msg = sysEnum.getMsg();
    }
    public SysMsg(SysEnum sysEnum, Object object){
        this.code = sysEnum.getCode();
        this.msg = sysEnum.getMsg();
        this.object = object;
    }
}
