package com.wrd.rpp.service;

import com.wrd.rpp.shiro.bean.UserInfo;

public interface UserService {
    public void save(UserInfo userInfo);
    public UserInfo findByAccountName(String accountName);
}
