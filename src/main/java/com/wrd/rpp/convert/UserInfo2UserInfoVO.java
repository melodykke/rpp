package com.wrd.rpp.convert;

import com.wrd.rpp.enums.UserAccountStatusEnum;
import com.wrd.rpp.shiro.bean.SysRole;
import com.wrd.rpp.shiro.bean.UserInfo;
import com.wrd.rpp.vo.UserInfoVO;
import org.springframework.beans.BeanUtils;

public class UserInfo2UserInfoVO {
    public static UserInfoVO convert(UserInfo userInfo, String regionName){
        UserInfoVO userInfoVO = new UserInfoVO();
        String roles = "";
        BeanUtils.copyProperties(userInfo, userInfoVO);
        if(userInfo.getState() == UserAccountStatusEnum.ACCOUNT_INACTIVATED.getCode()){
            userInfoVO.setState(UserAccountStatusEnum.ACCOUNT_INACTIVATED.getMsg());
        }else if(userInfo.getState() == UserAccountStatusEnum.ACCOUNT_ACTIVATED.getCode()){
            userInfoVO.setState(UserAccountStatusEnum.ACCOUNT_ACTIVATED.getMsg());
        }else{
            userInfoVO.setState("未知状态");
        }

        if(userInfo.getSysRoleList().size() > 0){
            for (SysRole sysRole : userInfo.getSysRoleList()) {
                roles += sysRole.getDescription();
            }
        }else {
            roles = "-未分配-";
        }
        userInfoVO.setRoles(roles);
        userInfoVO.setRegionName(regionName);
        return userInfoVO;
    }
}
