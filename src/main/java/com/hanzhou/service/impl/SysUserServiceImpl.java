package com.hanzhou.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanzhou.dao.SysUserDao;
import com.hanzhou.model.SysUser;
import com.hanzhou.service.SysUserService;

@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SysUserServiceImpl.class);

	@Autowired
	private SysUserDao userDao;

	/* 
	 * 根据用户名获取用户信息
	 * @see com.hanzhou.service.SysUserService#getByUserName(java.lang.String)
	 */
	@Override
	public SysUser getByUserName(String userName) {
		return userDao.getByUserName(userName);
	}

}
