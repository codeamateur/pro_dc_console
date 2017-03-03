package com.hanzhou.service;

import java.util.List;

import com.hanzhou.model.SysRole;

public interface SysRoleService {
	
	public List<SysRole> getListByUserId(String userId);

}
