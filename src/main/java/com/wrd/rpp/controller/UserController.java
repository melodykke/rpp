package com.wrd.rpp.controller;


import com.wrd.rpp.convert.ApplicationInfo2VO;
import com.wrd.rpp.convert.UserInfo2UserInfoVO;
import com.wrd.rpp.dataobject.ApplicationInfo;
import com.wrd.rpp.dataobject.PowerPlantBaseInfoUpload;
import com.wrd.rpp.dataobject.Region;
import com.wrd.rpp.enums.ApplicationStatusEnum;
import com.wrd.rpp.enums.ApplicationTypeEnum;
import com.wrd.rpp.enums.SysEnum;
import com.wrd.rpp.exception.SysException;
import com.wrd.rpp.form.AssignRolesForm;
import com.wrd.rpp.repository.RoleRepository;
import com.wrd.rpp.service.*;
import com.wrd.rpp.shiro.bean.UserInfo;
import com.wrd.rpp.util.KeyUtil;
import com.wrd.rpp.util.ResultUtil;
import com.wrd.rpp.vo.ApplicationVO;
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
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private RegionService regionService;
    @Autowired
    private ApplicationInfoService applicationInfoService;
    @GetMapping("/user-manage")
    @RequiresRoles("admin")
    public ModelAndView userManage(){
        ModelAndView mv = new ModelAndView("/user-manage");
        return mv;
    }
    @PostMapping("/user-manage")
    @RequiresRoles("admin")
    public ResultVO userManage(@RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex,
                             @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                             @RequestParam(value = "param", defaultValue = "") String param,
                             Map<String, Object> map){
        //前端bootstrap的table传来的参数为pageIndex和pageSize;pageIndex是从1开始的，而后台pageable的page计数是从0开始的
        Integer page = pageIndex-1;
        Integer size = pageSize;
        Sort sort = new Sort(Sort.Direction.ASC, "regionCode");
        PageRequest pageRequest = new PageRequest(page, size, sort);
        Page<UserInfoVO> userInfoVOPage = userService.listAll(pageRequest);
        return ResultUtil.success(userInfoVOPage);
    }


    @PostMapping("/assign-role")
    @RequiresRoles("admin")
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
    public ResultVO changeState(@RequestParam String username){
        String msg = userService.changeState(username);
        return ResultUtil.success(SysEnum.DATA_CONFIG_SUCCESS.getCode(), msg);
    }

    @GetMapping("/base-info-approval")
    @RequiresRoles(value = {"admin", "city"}, logical = Logical.OR)
    public ModelAndView baseInfoApproval(){
        ModelAndView mv = new ModelAndView("/power-plant-base-info-approval");
        return mv;
    }
    @PostMapping("/get-approval-list")
    @RequiresRoles(value = {"admin", "city"}, logical = Logical.OR)
    public ResultVO getApprovalList(){
        String thisUserRegionCode = ((UserInfo)SecurityUtils.getSubject().getPrincipal()).getRegionCode();
        Region thisUserRegion = regionService.findRegionByRegionCode(thisUserRegionCode);
        List<String> childrenRegionCodeList = new ArrayList<String>();
        for(Region region : thisUserRegion.getChildren()){
            childrenRegionCodeList.add(region.getRegionCode());
        }
        List<ApplicationInfo> applicationInfoList = applicationInfoService.findByRegionCodeInAndStatusIs(childrenRegionCodeList, ApplicationStatusEnum.HAVENT_APPROVAL_YET.getStatusCode());
        List<ApplicationVO> applicationVOList = ApplicationInfo2VO.convertList(applicationInfoList);
        return ResultUtil.success(applicationVOList);
    }
}
/*
@RequiresRoles("user")

//必须同时属于user和admin角色
@RequiresRoles({"user","admin"})

//属于user或者admin之一;修改logical为OR 即可
@RequiresRoles(value={"user","admin"},logical=Logical.OR)*/
