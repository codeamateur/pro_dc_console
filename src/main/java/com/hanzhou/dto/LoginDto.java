package com.hanzhou.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.hanzhou.model.SysUser;

public class LoginDto extends SysUser {
	@NotEmpty
	@Length(min = 6, max = 50)
	private String originalPassword;

	public String getOriginalPassword() {
		return originalPassword;
	}

	public void setOriginalPassword(String originalPassword) {
		this.originalPassword = originalPassword;
	}
	
	

}
