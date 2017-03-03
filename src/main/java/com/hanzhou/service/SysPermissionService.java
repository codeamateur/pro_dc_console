package com.hanzhou.service;

import java.util.List;

import com.hanzhou.model.SysPermission;

public interface SysPermissionService {
	
	public List<SysPermission> getListByRoleId(String roleId);

}
