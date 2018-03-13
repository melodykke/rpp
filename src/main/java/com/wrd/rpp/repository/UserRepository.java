package com.wrd.rpp.repository;

import com.wrd.rpp.shiro.bean.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface UserRepository extends JpaRepository<UserInfo, Integer>, JpaSpecificationExecutor<UserInfo>{
    UserInfo findByUsername(String username);
    UserInfo findUserInfoByRegionCode(String regionCode);
}
