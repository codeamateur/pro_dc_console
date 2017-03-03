package com.hanzhou.dao;

import java.util.List;

import com.hanzhou.model.SysRole;

public interface SysRoleDao {
    int deleteByPrimaryKey(String id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);
    
    List<SysRole> getListByUserId(String userId);
}