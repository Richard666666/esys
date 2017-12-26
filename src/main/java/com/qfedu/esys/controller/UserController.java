package com.qfedu.esys.controller;

import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.qfedu.common.entity.WoPage;
import com.qfedu.common.entity.WoResultCode;
import com.qfedu.common.util.WoUtil;
import com.qfedu.esys.dto.UserDto;
import com.qfedu.esys.service.IUserService;
import com.qfedu.esys.vo.GridEuiVo;

@RestController
@RequestMapping("/sys/user/")
public class UserController {
	private final static Logger LOG = LogManager.getLogger(UserController.class);

	@Resource // @Autowired
	private IUserService userService;

	@RequestMapping("/list")
	public GridEuiVo<UserDto> getList(String searchContent, Long page, Long rows) {
		WoPage<UserDto> dto = userService.getList(searchContent, (page - 1) * rows, rows);
		return new GridEuiVo<UserDto>(dto);
	}

	@RequestMapping("/delete")
	public WoResultCode delete(String woSelectedIds) {
		String[] ids = WoUtil.splitIds(woSelectedIds);
		userService.delete(ids);
		return WoResultCode.getSuccessCode();
	}

	@RequestMapping("/create")
	public WoResultCode create(UserDto u, MultipartFile headImageFile) {
		try {
			if (headImageFile != null && !headImageFile.isEmpty()) {
				// 构造相对路径
				String path = "upload/" + headImageFile.getOriginalFilename();
				// 构造头像图片文件的全路径 // req.getServletContext().getRealPath("/")
				String fullPath = WoUtil.getWoRoot() + "/" + path;
				// 写入文件
				headImageFile.transferTo(new File(fullPath));
				// 相对路径设置到dto
				u.setHeadImage(path);
			}
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		userService.create(u);
		return WoResultCode.getSuccessCode();
	}

	@RequestMapping("/update")
	public WoResultCode update(UserDto u) {
		userService.update(u);
		return WoResultCode.getSuccessCode();
	}

	@Resource
	@Qualifier("userExcelView") // 指定实现接口的类名称
	private View userExcelView;
	
	@RequestMapping("/export")
	public ModelAndView exportUsers () {
		ModelAndView mav = new ModelAndView();
		// 设置excel视图对象
		mav.setView(userExcelView);
		// 设置视图对象需要的数据
		mav.addObject("users", userService.getList(null, 0L, 10L).getRows());
		return mav;
	}
}
