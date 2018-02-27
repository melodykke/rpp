package com.wrd.rpp.service;

import com.wrd.rpp.shiro.bean.UserInfo;
import com.wrd.rpp.vo.UserInfoVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    void save(UserInfo userInfo);
    UserInfo findByUsername(String username);
    UserInfo findUserInfoByRegionCode(String regionCode);
    Page<UserInfoVO> ListAll(Pageable pageable);
    String changeState(String username);
}
