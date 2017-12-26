package com.qfedu.esys.controller;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.qfedu.common.entity.WoResultCode;
import com.qfedu.esys.ESysException;
import com.qfedu.esys.dto.UserDto;
import com.qfedu.esys.service.IUserService;
import com.qfedu.esys.util.ESysConstant;

@Controller
@RequestMapping("/")
// @SessionAttributes表示在Controller设置的共享域对象，如果key值等于names设置的值，则相应的对象会设置到session中
@SessionAttributes(names = ESysConstant.SESSION_USER)
public class HomeController {
	private final static Logger LOG = LogManager.getLogger(HomeController.class);
	
	/**
	 * @return
	 */
	@RequestMapping ("/x")
	public ModelAndView toHome (HttpServletRequest req) {
		String msg = "欢迎您！";
		Boolean woLogin = false;
		// 从session获取用户信息，从而判断是否登录
		UserDto u = (UserDto)req.getSession().getAttribute(ESysConstant.SESSION_USER);
		if (u != null) {
			msg = u.getLoginName() + "," + msg;
			woLogin = true;
		}
		ModelAndView m = new ModelAndView ();
		m.addObject("woWelcomeMsg", msg);
		m.addObject("woLogin", woLogin);
		m.setViewName("main");
		return m;
	}
	
	@RequestMapping ("/")
	public String toHome2 (Map<String, Object> model) {
		String msg = "欢迎您！";
		Boolean woLogin = false;
		// 从session获取用户信息，从而判断是否登录
		UserDto u = (UserDto)model.get(ESysConstant.SESSION_USER);
		if (u != null) {
			msg = u.getLoginName() + "," + msg;
			woLogin = true;
		}
		model.put("woWelcomeMsg", msg);
		model.put("woLogin", woLogin);
		return "main";
	}
	
	
	/**
	 * @PathVariable注解，将url中的{}中的变量值，映射到Controller的方法入参
	 * @param pagePath
	 * @return
	 */
	@RequestMapping(value="/page/{pagePath}") 
	public String page (@PathVariable String pagePath){
		return pagePath;
	}
	
	@Resource
	private IUserService userService;
	
	@ResponseBody
	@RequestMapping (value="/authentication", produces = ESysConstant.APP_JSON)
	public WoResultCode login (String user, String password, Map<String, Object> model) {
		try {
			UserDto u = userService.authenticate (user, password);
			model.put (ESysConstant.SESSION_USER, u);
			return WoResultCode.getSuccessCode();
		} catch (ESysException e) {
			return e.getCode();
		}
	}
	
	@ResponseBody
	@RequestMapping (value="/logout", produces = ESysConstant.APP_JSON)
	public WoResultCode logout (Map<String, Object> map, SessionStatus sessionStatus) {
		// SessionStatus.setComplete必须调用，否则map.remove不会生效
		map.remove(ESysConstant.SESSION_USER);
		sessionStatus.setComplete();
		return WoResultCode.getSuccessCode();
	}
}
