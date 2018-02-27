package com.wrd.rpp.repository;

import com.wrd.rpp.shiro.bean.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<SysRole, Integer> {
    SysRole findByDescription(String description);
}
