package com.wrd.rpp.service.impl;


import com.wrd.rpp.enums.SysEnum;
import com.wrd.rpp.exception.SysException;
import com.wrd.rpp.repository.RoleRepository;
import com.wrd.rpp.service.RoleService;
import com.wrd.rpp.shiro.bean.SysRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<SysRole> findByDescriptions(List<String> descriptions) {
        List<SysRole> sysRoleList = new ArrayList<>();
        for(String description : descriptions){
            if(roleRepository.findByDescription(description) == null){
                log.error("【角色分配】 通过description找不到相应的SysRole对象");
                throw new SysException(SysEnum.ASSIGNROLES_PARAM_ERROR);
            }
            sysRoleList.add(roleRepository.findByDescription(description));
        }
        return sysRoleList;
    }
}
