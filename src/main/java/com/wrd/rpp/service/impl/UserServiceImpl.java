package com.wrd.rpp.service.impl;

import com.wrd.rpp.convert.UserInfo2UserInfoVO;
import com.wrd.rpp.enums.SysEnum;
import com.wrd.rpp.exception.SysException;
import com.wrd.rpp.repository.UserRepository;
import com.wrd.rpp.service.UserService;
import com.wrd.rpp.shiro.bean.UserInfo;
import com.wrd.rpp.vo.UserInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(UserInfo userInfo) {
        userRepository.save(userInfo);
    }

    @Override
    public UserInfo findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserInfo findByRegionName(String regionName) {
        return userRepository.findByRegionName(regionName);
    }

    @Override
    public Page<UserInfoVO> ListAll(Pageable pageable) {
        Page<UserInfo> userInfoPage = userRepository.findAll(pageable);
        List<UserInfo> userInfoList = userInfoPage.getContent();
        if(userInfoList.size() <= 0){
            log.info("【用户管理】 查询用户错误，查询页无内容！");
            throw new SysException(SysEnum.PAGE_NO_CONTENT);
        }
        List<UserInfoVO> userInfoVOList = userInfoList.stream().map(e -> UserInfo2UserInfoVO.convert(e)).collect(Collectors.toList());
        Page<UserInfoVO> userInfoVOPage = new PageImpl<UserInfoVO>(userInfoVOList, pageable, userInfoPage.getTotalElements());
        return userInfoVOPage;
    }


}
