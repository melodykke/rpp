package com.wrd.rpp.service;

import com.wrd.rpp.shiro.bean.UserInfo;
import com.wrd.rpp.vo.UserInfoVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    public void save(UserInfo userInfo);
    public UserInfo findByUsername(String username);
    public UserInfo findByRegionName(String regionName);
    public Page<UserInfoVO> ListAll(Pageable pageable);
}
