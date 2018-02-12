package com.wrd.rpp.controller;

import com.wrd.rpp.dataobject.PowerPlantBaseInfoUpload;
import com.wrd.rpp.enums.SysEnum;
import com.wrd.rpp.exception.SysException;
import com.wrd.rpp.form.PowerPlantBaseInfoForm;
import com.wrd.rpp.form.UserRegistryForm;
import com.wrd.rpp.form.UserSigninForm;
import com.wrd.rpp.msg.SysMsg;
import com.wrd.rpp.service.PowerPlantService;
import com.wrd.rpp.service.UserService;
import com.wrd.rpp.shiro.bean.UserInfo;
import com.wrd.rpp.util.ResultUtil;
import com.wrd.rpp.util.UserUtil;
import com.wrd.rpp.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PowerPlantService powerPlantService;

    @GetMapping("/power-plant-base-info-upload")
    public ModelAndView powerPlantBaseInfoUpload(){
        ModelAndView modelAndView = new ModelAndView("power-plant-base-info-upload");
        return modelAndView;
    }
    @PostMapping("/power-plant-base-info-upload")
    public ResultVO<PowerPlantBaseInfoUpload> powerPlantBaseInfoUpload(@Valid PowerPlantBaseInfoForm powerPlantBaseInfoForm, BindingResult bindingResult, Map<String, Object> map){
        if(bindingResult.hasErrors()){
            log.error("【电站信息】 电站基础信息填报错误， 参数不正确 powerPlantBaseInfoForm = {}， 错误：{}", powerPlantBaseInfoForm, bindingResult.getFieldError().getDefaultMessage());
            throw new SysException(SysEnum.PARAM_ERROR);
        }
        SysMsg sysMsg = powerPlantService.savePowerPlantBaseInfoUpload(powerPlantBaseInfoForm);
        return ResultUtil.success(sysMsg.getObject());
    }



}
