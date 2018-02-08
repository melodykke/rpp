package com.wrd.rpp.controller;

import com.wrd.rpp.enums.SysEnum;
import com.wrd.rpp.exception.SysException;
import com.wrd.rpp.form.UserRegistryForm;
import com.wrd.rpp.form.UserSigninForm;
import com.wrd.rpp.service.UserService;
import com.wrd.rpp.shiro.bean.UserInfo;
import com.wrd.rpp.util.ResultUtil;
import com.wrd.rpp.util.UserUtil;
import com.wrd.rpp.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/")
@Slf4j
public class MainController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/loginUI"})
    public ModelAndView loginUI(){
        ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;
    }

    @RequestMapping(value = {"/403"})
    public ModelAndView To403(){
        ModelAndView modelAndView = new ModelAndView("403");
        return modelAndView;
    }

    /**
     * 用户账号登录
     * @param userSigninForm
     * @param bindingResult
     * @return
     */
    @PostMapping("/login")
    public ModelAndView login (@Valid UserSigninForm userSigninForm, BindingResult bindingResult, HttpServletRequest request,
                         Map<String, Object> map){
        ModelAndView modelAndView = new ModelAndView("login");
        if(bindingResult.hasErrors()){
            log.error("【账号错误】 账号登录错误， 参数不正确 userSigninForm = {}", userSigninForm);
            throw new SysException(SysEnum.SIGNIN_PARAM_ERROR);
        }
        String exception = (String)request.getAttribute("shiroLoginFailure");
        System.out.println("exception: "+exception);
        String msg = "";
        if (exception != null) {
            if (UnknownAccountException.class.getName().equals(exception)) {
                System.out.println("UnknownAccountException -- > 账号不存在：");
                msg = "UnknownAccountException -- > 账号不存在：";
            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
                System.out.println("IncorrectCredentialsException -- > 密码不正确：");
                msg = "IncorrectCredentialsException -- > 密码不正确：";
            } else if ("kaptchaValidateFailed".equals(exception)) {
                System.out.println("kaptchaValidateFailed -- > 验证码错误");
                msg = "kaptchaValidateFailed -- > 验证码错误";
            } else {
                msg = "else >> "+exception;
                System.out.println("else -- >" + exception);
            }
        }
        map.put("msg", msg);
        // 此方法不处理登录成功,由shiro进行处理
        return modelAndView;
    }

    /**
     * 用户账户注册
     * @param userRegistryForm
     * @param bindingResult
     * @return
     */
    @PostMapping("/register")
    public ResultVO add(@Valid UserRegistryForm userRegistryForm, BindingResult bindingResult){
        //表单后台验证
        if(bindingResult.hasErrors()){
            log.error("【账号错误】 账号注册错误， 参数不正确 userRegistryForm = {}", userRegistryForm);
            throw new SysException(SysEnum.REGISTRY_PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        //检查accountName在后台是否已经存在
        if(userService.findByUsername(userRegistryForm.getUsername())!=null){
            log.error("【账号错误】 账号注册错误， 所注册账号名已经存在， userRegistryForm.username = {}", userRegistryForm.getUsername());
            throw new SysException(SysEnum.REGISTRY_DUPLICATED_ACCOUNTNAME);
        }
        UserInfo defaultUser = UserUtil.createDefaultUser(userRegistryForm);
        userService.save(defaultUser);
        return ResultUtil.success(defaultUser);
    }
}
