package com.wrd.rpp.service;

import com.wrd.rpp.shiro.bean.SysRole;

import java.util.List;

public interface RoleService {
    List<SysRole> findByDescriptions(List<String> descriptions);
}
