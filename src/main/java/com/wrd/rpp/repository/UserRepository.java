package com.wrd.rpp.repository;

import com.wrd.rpp.shiro.bean.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserInfo, Integer> {
    UserInfo findByUsername(String username);
    UserInfo findUserInfoByRegionCode(String regionCode);
}
