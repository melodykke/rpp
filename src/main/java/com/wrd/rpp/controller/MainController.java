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
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.ManyToMany;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/")
@Slf4j
public class MainController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/index", "/"})
    public String index(Map<String, Object> map){
    /*    ModelAndView modelAndView = new ModelAndView("index");*/
        Subject subject = SecurityUtils.getSubject();
        UserInfo userInfo = (UserInfo)subject.getPrincipal();
       /* modelAndView.getModel().put("userInfo", userInfo);*/
        map.put("userInfo", userInfo);
        return "index";
    }

    /**
     * 用户账号登录页面 *******个人经验 shiro机制，在登录时，登录页面路径和登录动作路径一致，均为login，页面登录为RequestMethod.GET
     *                        而登录动作为RequestMethod.Post,以此区分。*******
     * @param userSigninForm
     * @param bindingResult
     * @return
     */
    @GetMapping(value = {"/login"})
    public String loginUI(){
        /*ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;*/
        return "login";
    }
    /**
     * 用户账号登录 若在realm中的doAuthentication判断为非法，则出发该方法，否则顺利按照shiroConfiguration中配置的setSuccessUrl跳转。
     * @param userSigninForm
     * @param bindingResult
     * @return
     */
    @PostMapping("/login")
    public String login (@Valid UserSigninForm userSigninForm, BindingResult bindingResult, HttpServletRequest request,
                         Map<String, Object> map){
      /*  ModelAndView modelAndView = new ModelAndView("login");*/
        if(bindingResult.hasErrors()){
            log.error("【账号错误】 账号登录错误， 参数不正确 userSigninForm = {}", userSigninForm);
            throw new SysException(SysEnum.SIGNIN_PARAM_ERROR);
        }
        String exception = (String)request.getAttribute("shiroLoginFailure");
        System.out.println("exception: "+exception);
        String msg = "";
        if (exception != null) {
            if (UnknownAccountException.class.getName().equals(exception)) {
                log.error("UnknownAccountException -- > 账号不存在  userSigninForm = {}", userSigninForm);
                msg = "账号不存在，请重新输入！";
            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
                log.error("IncorrectCredentialsException -- > 密码不正确：", userSigninForm);
                msg = "密码不正确，请重新输入！";
            } else if ("kaptchaValidateFailed".equals(exception)) {
                log.error("kaptchaValidateFailed -- > 验证码错误", userSigninForm);
                msg = "验证码错误，请重新输入！";
            } else {
                log.error("else -- >" + exception);
                msg = "未知错误，请稍后重试 ";
            }
        }
        map.put("msg", msg);
        // 此方法不处理登录成功,由shiro进行处理
        return "login";
    }

    @RequestMapping(value = {"/403"})
    public String To403(){
    /*    ModelAndView modelAndView = new ModelAndView("403");*/
        return "403";
    }



    /**
     * 用户账户注册
     * @param userRegistryForm
     * @param bindingResult
     * @return
     */
    @PostMapping("/register")
    @ResponseBody
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
