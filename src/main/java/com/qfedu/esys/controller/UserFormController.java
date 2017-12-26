package com.qfedu.esys.controller;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qfedu.esys.service.IUserService;

@Controller
@RequestMapping("/sys/user/")
public class UserFormController {
	private final static Logger LOG = LogManager.getLogger(UserFormController.class);

	@Resource // @Autowired
	private IUserService userService;

	@RequestMapping("/importForm")
	public String getList(String loginName, Long page, Long rows) {
		return "user/importForm";
	}

}
