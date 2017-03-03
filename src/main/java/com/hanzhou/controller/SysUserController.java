package com.hanzhou.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hanzhou.common.util.Md5Util32;
import com.hanzhou.dto.LoginDto;

@Controller
public class SysUserController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SysUserController.class);

	@RequestMapping(value = "/login",method = RequestMethod.GET)
    public String showLoginForm(HttpServletRequest req, Model model) {
        String exceptionClassName = (String)req.getAttribute("shiroLoginFailure");
        String error = null;
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isAuthenticated()) {
        	return "redirect:/";
        }
        if(UnknownAccountException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if (LockedAccountException.class.getName().equals(exceptionClassName)) {
        	error = "账户被锁定";
        } else if (exceptionClassName != null) {
            error = "其他错误：" + exceptionClassName;
        }
        model.addAttribute("error", error);
        return "login";
    }
	
//	@RequestMapping(value = "/login",method = RequestMethod.POST)
//    public String login(@Valid LoginDto login,BindingResult result) throws Exception {
//		UsernamePasswordToken token = new UsernamePasswordToken();
//	    token.setUsername(login.getName());
//	    token.setPassword(Md5Util32.md5(login.getOriginalPassword()).toCharArray());  
//	    SecurityUtils.getSubject().login(token);
//		if (result.hasErrors()) {
//			return "login";
//		} else {
//			return "redirect:/";
//		}
//    }
	
	
	@RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(LoginDto login){
		try {		
		UsernamePasswordToken token = new UsernamePasswordToken();
	    token.setUsername(login.getName());
	    token.setPassword(Md5Util32.md5(login.getOriginalPassword()).toCharArray());  
	    SecurityUtils.getSubject().login(token);
	    return "redirect:/";
		}catch(UnknownAccountException uae){
			LOGGER.error("weblogin UnknownAccount: "+uae.toString());
		}catch(IncorrectCredentialsException ice){
			LOGGER.error("weblogin IncorrectCredentials: "+ice.toString());
		}catch(Exception e){
			LOGGER.error("weblogin error: ",e);
		}
		return "redirect:/login";
    }
	
	@RequestMapping(value = "/")
    public ModelAndView home(HttpServletRequest req, Model model) {
		Subject currentUser = SecurityUtils.getSubject();
		ModelAndView mav = new ModelAndView("loginSuccess");
		model.addAttribute("userId", currentUser.getPrincipal());
        return mav;
    }

	@RequiresPermissions("test:add")
	@RequestMapping(value = "/add")
    public ModelAndView add(HttpServletRequest req, Model model) {
		ModelAndView mav = new ModelAndView("Done");
        return mav;
    }
	
	
}
