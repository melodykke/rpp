package com.wrd.rpp.repository;

import com.wrd.rpp.shiro.bean.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserInfo, Integer> {
    public UserInfo findByAccountName(String userRepository);
}
