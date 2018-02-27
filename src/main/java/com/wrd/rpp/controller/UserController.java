package com.wrd.rpp.controller;


import com.wrd.rpp.convert.UserInfo2UserInfoVO;
import com.wrd.rpp.enums.SysEnum;
import com.wrd.rpp.exception.SysException;
import com.wrd.rpp.form.AssignRolesForm;
import com.wrd.rpp.repository.RoleRepository;
import com.wrd.rpp.service.RoleService;
import com.wrd.rpp.service.UserService;
import com.wrd.rpp.shiro.bean.UserInfo;
import com.wrd.rpp.util.ResultUtil;
import com.wrd.rpp.vo.ResultVO;
import com.wrd.rpp.vo.UserInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @GetMapping("/user-manage")
    @RequiresRoles("admin")
    public String userManage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                             @RequestParam(value = "size", defaultValue = "5") Integer size,
                             Map<String, Object> map){
        PageRequest pageRequest = new PageRequest(page, size);
        Page<UserInfoVO> userInfoVOPage = userService.ListAll(pageRequest);
        map.put("userInfoVOPage", userInfoVOPage);
        return "/user-manage";
    }

    @PostMapping("/assign-role")
    @RequiresRoles("admin")
    @ResponseBody
    public ResultVO assignRoles(@Valid AssignRolesForm assignRolesForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error("【角色分配】 参数不正确 assignRolesForm = {}", assignRolesForm);
            throw new SysException(SysEnum.ASSIGNROLES_PARAM_ERROR);
        }
        if(userService.findByUsername(assignRolesForm.getUsername()) == null){
            log.error("【角色分配】 通过username找不到相应UserInfo对象 assignRolesForm = {}", assignRolesForm);
            throw new SysException(SysEnum.ASSIGNROLES_PARAM_ERROR);
        }
        System.out.println(assignRolesForm);
        UserInfo userInfo = userService.findByUsername(assignRolesForm.getUsername());
        userInfo.setSysRoleList(roleService.findByDescriptions(assignRolesForm.getRoles()));
        userService.save(userInfo);
        return ResultUtil.success(SysEnum.DATA_CONFIG_SUCCESS.getCode(), "角色配置成功，请等待窗口自动关闭，您也可以手动关闭！");
    }

    @GetMapping("/change-state")
    @RequiresRoles(value = "admin")
    @ResponseBody
    public ResultVO changeState(@RequestParam String username){
        String msg = userService.changeState(username);
        return ResultUtil.success(SysEnum.DATA_CONFIG_SUCCESS.getCode(), msg);
    }


}
