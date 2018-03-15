package com.wrd.rpp.service.impl;

import com.wrd.rpp.convert.UserInfo2UserInfoVO;
import com.wrd.rpp.dataobject.Region;
import com.wrd.rpp.enums.SysEnum;
import com.wrd.rpp.enums.UserAccountStatusEnum;
import com.wrd.rpp.exception.SysException;
import com.wrd.rpp.repository.RegionRepository;
import com.wrd.rpp.repository.UserRepository;
import com.wrd.rpp.service.UserService;
import com.wrd.rpp.shiro.bean.UserInfo;
import com.wrd.rpp.vo.UserInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RegionRepository regionRepository;



    @Override
    public void save(UserInfo userInfo) {
        userRepository.save(userInfo);
    }

    @Override
    public UserInfo findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserInfo findUserInfoByRegionCode(String regionCode) {
        return userRepository.findUserInfoByRegionCode(regionCode);
    }


    @Override
    public Page<UserInfoVO> listAll(Pageable pageable) {
/*        Page<UserInfo> userInfoPage = userRepository.findAll(new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                Path path = root.get("state");
                return cb.equal(path, 1);
            }
        }, pageable);*/
        Page<UserInfo> userInfoPage = userRepository.findAll(pageable);
        List<UserInfo> userInfoList = userInfoPage.getContent();
        if(userInfoList.size() <= 0){
            log.info("【用户管理】 查询用户错误，查询页无内容！");
            throw new SysException(SysEnum.PAGE_NO_CONTENT);
        }
        List<UserInfoVO> userInfoVOList = userInfoList.stream().map(e -> UserInfo2UserInfoVO.convert(e, regionRepository.findRegionByRegionCode(e.getRegionCode()).getRegionName())).collect(Collectors.toList());
        Page<UserInfoVO> userInfoVOPage = new PageImpl<UserInfoVO>(userInfoVOList, pageable, userInfoPage.getTotalElements());
        return userInfoVOPage;
    }

    @Override
    public String changeState(String username) {
        UserInfo userInfo = userRepository.findByUsername(username);
        if(userInfo.getState()==0){
            userInfo.setState((byte)1);
        }else if(userInfo.getState()==1){
            userInfo.setState((byte)0);
        }
        UserInfo userInfo1 = userRepository.save(userInfo);
        return userInfo1.getState()==0 ? UserAccountStatusEnum.ACCOUNT_INACTIVATED.getMsg() : UserAccountStatusEnum.ACCOUNT_ACTIVATED.getMsg();
    }

    //找到当前用户的上级用户
    @Override
    public UserInfo findParentByUsername(String username) {
        UserInfo thisUser = userRepository.findByUsername(username);
        UserInfo parentUser = thisUser.getParent();
        return parentUser;
    }


}
