package com.wrd.rpp.controller;


import com.wrd.rpp.convert.UserInfo2UserInfoVO;
import com.wrd.rpp.enums.SysEnum;
import com.wrd.rpp.exception.SysException;
import com.wrd.rpp.service.UserService;
import com.wrd.rpp.shiro.bean.UserInfo;
import com.wrd.rpp.util.ResultUtil;
import com.wrd.rpp.vo.ResultVO;
import com.wrd.rpp.vo.UserInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user-manage")
    @RequiresRoles("admin")
    public String userManage(){
        return "/user-manage";
    }
    @GetMapping("/list-users")
    @ResponseBody
    public ResultVO listUsers(@RequestParam(value = "page", defaultValue = "0") Integer page,
                              @RequestParam(value = "size", defaultValue = "10") Integer size){
        PageRequest pageRequest = new PageRequest(page, size);
        Page<UserInfoVO> userInfoVOPage = userService.ListAll(pageRequest);
        return ResultUtil.success(userInfoVOPage.getContent());
    }


}
