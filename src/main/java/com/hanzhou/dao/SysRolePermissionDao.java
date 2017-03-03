package com.hanzhou.dao;

import com.hanzhou.model.SysRolePermission;

public interface SysRolePermissionDao {
    int insert(SysRolePermission record);

    int insertSelective(SysRolePermission record);
}