package com.wrd.rpp.util;


import com.wrd.rpp.enums.SysEnum;
import com.wrd.rpp.vo.ResultVO;

public class ResultUtil {

    public static ResultVO success(){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(SysEnum.DATA_CALLBACK_SUCCESS.getCode());
        resultVO.setMsg(SysEnum.DATA_CALLBACK_SUCCESS.getMsg());
        return resultVO;
    }

    public static ResultVO success(Object object){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(SysEnum.DATA_CALLBACK_SUCCESS.getCode());
        resultVO.setMsg(SysEnum.DATA_CALLBACK_SUCCESS.getMsg());
        resultVO.setData(object);
        return resultVO;
    }

    public static ResultVO failed(){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(SysEnum.DATA_CALLBACK_FAILED.getCode());
        resultVO.setMsg(SysEnum.DATA_CALLBACK_FAILED.getMsg());
        return resultVO;
    }
}
