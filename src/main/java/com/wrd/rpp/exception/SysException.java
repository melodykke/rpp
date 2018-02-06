package com.wrd.rpp.exception;

import com.wrd.rpp.enums.SysEnum;
import lombok.Data;

@Data
public class SysException extends RuntimeException {

    private Integer code;

    public SysException(SysEnum sysEnum){
        super(sysEnum.getMsg());
        this.code = sysEnum.getCode();
    }

    public SysException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }
}
