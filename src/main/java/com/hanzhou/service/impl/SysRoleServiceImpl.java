package com.hanzhou.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanzhou.dao.SysRoleDao;
import com.hanzhou.model.SysRole;
import com.hanzhou.service.SysRoleService;

@Service("sysRoleService")
public class SysRoleServiceImpl implements SysRoleService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SysRoleServiceImpl.class);
	
	@Autowired
	private SysRoleDao roleDao;

	/* 
	 * 根据用户id获取角色列表
	 * @see com.hanzhou.service.SysRoleService#getListByUserId(java.lang.String)
	 */
	@Override
	public List<SysRole> getListByUserId(String userId) {
		return roleDao.getListByUserId(userId);
	}

}
