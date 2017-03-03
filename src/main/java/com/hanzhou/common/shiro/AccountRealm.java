package com.hanzhou.common.shiro;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.hanzhou.model.SysPermission;
import com.hanzhou.model.SysRole;
import com.hanzhou.model.SysUser;
import com.hanzhou.service.SysPermissionService;
import com.hanzhou.service.SysRoleService;
import com.hanzhou.service.SysUserService;

public class AccountRealm extends AuthorizingRealm {
	
	@Autowired
	protected SysUserService sysUserService;
	@Autowired
	protected SysRoleService sysRoleService;
	@Autowired
	protected SysPermissionService sysPermissionService;	
	
	/**
	 * 认证回调函数,登录时调用
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		SysUser user = null;
		//获取用户信息实体
		user = sysUserService.getByUserName(token.getUsername());
     	if (user == null){
     		throw new UnknownAccountException();
     	} 
        if(Boolean.TRUE.equals(user.getDelFlg())) {
            throw new LockedAccountException();
        }
		return new SimpleAuthenticationInfo(user.getId(),
				user.getPassword().toCharArray(),getName());
	}
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		String userId = (String) principals.getPrimaryPrincipal();
		List<SysRole> roleList = sysRoleService.getListByUserId(userId);
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		List<String> roles = new ArrayList<String>();
		List<String> permissions = new ArrayList<String>();
		if(roleList != null && roleList.size()>0){
			for (SysRole role : roleList) {
				roles.add(role.getRoleName());
				List<SysPermission> permissionList = sysPermissionService.getListByRoleId(role.getId());
				if(permissionList != null && permissionList.size()>0){
					for(SysPermission permission :permissionList){
						permissions.add(permission.getPermissionDesc());
					}
				}
			}			
		}
		//基于Role的权限信息
		info.addRoles(roles);
		//基于Permission的权限信息
		info.addStringPermissions(permissions);
		return info;
	}
}
