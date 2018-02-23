package com.wrd.rpp.handle;

import com.wrd.rpp.exception.SysException;
import com.wrd.rpp.util.ResultUtil;
import com.wrd.rpp.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.transform.Result;

@ControllerAdvice
@Slf4j
public class SysExceptionHandler {

    @ExceptionHandler({SysException.class})
    @ResponseBody
    public ResultVO handle(Exception e){
        if(e instanceof SysException){
            log.info("【系统异常】" + e);
            return ResultUtil.failed(((SysException)e).getCode(), e.getMessage());
        }else{
            log.info("【未知异常】" + e);
            return ResultUtil.failed();
        }

    }


}
