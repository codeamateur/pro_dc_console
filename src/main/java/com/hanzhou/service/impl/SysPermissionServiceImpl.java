package com.hanzhou.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanzhou.dao.SysPermissionDao;
import com.hanzhou.model.SysPermission;
import com.hanzhou.service.SysPermissionService;

@Service("sysPermissionService")
public class SysPermissionServiceImpl implements SysPermissionService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SysPermissionServiceImpl.class);
	
	@Autowired
	private SysPermissionDao permissionDao;

	@Override
	public List<SysPermission> getListByRoleId(String roleId) {
		return permissionDao.getListByRoleId(roleId);
	}



}
