/**
 * 
 */
package com.qfedu.scene.controller;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.qfedu.common.entity.WoPage;
import com.qfedu.common.entity.WoResultCode;
import com.qfedu.common.util.WoUtil;



import com.qfedu.esys.util.ESysConstant;
import com.qfedu.esys.vo.GridEuiVo;
import com.qfedu.scene.dto.StaffDto;
import com.qfedu.scene.entity.Staff;
import com.qfedu.scene.service.IStaffService;

/**
 * @author cailei
 *
 */
@RestController
@RequestMapping(value="/sys/staff/",produces = ESysConstant.APP_JSON)
@SessionAttributes(names = ESysConstant.SESSION_USER)
public class StaffController {
	private final static Logger LOG = LogManager.getLogger(StaffController.class);

	@Resource // @Autowired
	private IStaffService staffService;

	@RequestMapping("/list")
	public GridEuiVo<StaffDto> getList(String searchContent, Long page, Long rows) {
		WoPage<StaffDto> dto = staffService.getList(searchContent, (page - 1) * rows, rows);
		return new GridEuiVo<StaffDto>(dto);
	}
	
	@RequestMapping("/delete")
	public WoResultCode delete(String woSelectedIds) {
		String[] ids = WoUtil.splitIds(woSelectedIds);
		staffService.delete(ids);
		return WoResultCode.getSuccessCode();
	}
	@RequestMapping("/create")
	public WoResultCode create(StaffDto u, MultipartFile headImageFile) {
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
		staffService.create(u);
		return WoResultCode.getSuccessCode();
	}

	@RequestMapping("/update")
	public WoResultCode update(StaffDto u) {
		staffService.update(u);
		return WoResultCode.getSuccessCode();
	}



}
