package com.wrd.rpp.handle;

import com.wrd.rpp.exception.SysException;
import com.wrd.rpp.util.ResultUtil;
import com.wrd.rpp.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.transform.Result;

@ControllerAdvice
@Slf4j
public class SysExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResultVO handle(Exception e){
        if(e instanceof SysException){
            return ResultUtil.failed();
        }else{
            log.info("【系统异常】 " + e);
            ResultVO resultVO = new ResultVO();
            resultVO.setCode(505);
            resultVO.setMsg("系统异常, 请稍后重试！");
            return resultVO;
        }
    }

}
