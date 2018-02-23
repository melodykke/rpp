package com.wrd.rpp.convert;

import com.wrd.rpp.shiro.bean.UserInfo;
import com.wrd.rpp.vo.UserInfoVO;
import org.springframework.beans.BeanUtils;

public class UserInfo2UserInfoVO {
    public static UserInfoVO convert(UserInfo userInfo){
        UserInfoVO userInfoVO = new UserInfoVO();
        BeanUtils.copyProperties(userInfo, userInfoVO);
        return userInfoVO;
    }
}
