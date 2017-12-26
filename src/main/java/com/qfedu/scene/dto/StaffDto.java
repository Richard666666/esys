
package com.qfedu.scene.dto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.query.Query;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.qfedu.common.entity.WoPage;
import com.qfedu.common.util.WoConstant;
import com.qfedu.common.util.WoUtil;
import com.qfedu.esys.dto.UserDto;
import com.qfedu.esys.entity.Role;
import com.qfedu.esys.entity.User;
import com.qfedu.scene.entity.Scene;
import com.qfedu.scene.entity.Staff;



	public class StaffDto {

		private String id;
		private String name;
		private String sex;
		@DateTimeFormat(pattern = "yyyy-MM-dd")  
		private Date birthday;
		private String headImage;
		private String mobile;
		private String email;
		/**
		 * 1-管理者 2-普通人员
		 */
		private String position;
		private String sceneId;
		private String sceneName;
		private String userId;
		private String loginName;
		private String password;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getSex() {
			return sex;
		}
		public void setSex(String sex) {
			this.sex = sex;
		}
		public Date getBirthday() {
			return birthday;
		}
		public void setBirthday(Date birthday) {
			this.birthday = birthday;
		}
		public String getHeadImage() {
			return headImage;
		}
		public void setHeadImage(String headImage) {
			this.headImage = headImage;
		}
		public String getMobile() {
			return mobile;
		}
		public void setMobile(String mobile) {
			this.mobile = mobile;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPosition() {
			return position;
		}
		public void setPosition(String position) {
			this.position = position;
		}
		public String getSceneId() {
			return sceneId;
		}
		public void setSceneId(String sceneId) {
			this.sceneId = sceneId;
		}
		public String getSceneName() {
			return sceneName;
		}
		public void setSceneName(String sceneName) {
			this.sceneName = sceneName;
		}
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}
		public String getLoginName() {
			return loginName;
		}
		public void setLoginName(String loginName) {
			this.loginName = loginName;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		

	


	
	public StaffDto() {
		
	}
	
	public StaffDto(Staff s) {
		super();
		this.id =s.getId();
		this.name =s.getName();
		this.sex =s.getSex();
		this.birthday =s.getBirthday();
		this.headImage = s.getHeadImage();
		this.mobile =s.getMobile();
		this.email = s.getEmail();
		this.position = s.getPosition();
				
		if(s.getScene()!=null){
			this.sceneId=s.getScene().getId();
			this.sceneName=s.getScene().getName();
		}
		if(s.getUser()!=null){
			this.userId=s.getUser().getId();
			
		}
	}

	public Staff createEntity () {
    	Staff s = new Staff();
    	// 第一步:设置简单属性
    	s.setId(id);
    	s.setName(name);
    	s.setSex(sex);
    	s.setBirthday(birthday);
    	s.setHeadImage(headImage);
    	s.setMobile(mobile);
    	s.setEmail(email);
    	s.setPosition(position);
    	
    	
    	//第二步:设置关系数据
    	Scene scene=new Scene();
    	if(!WoUtil.isEmpty(sceneId)){
    		scene.setId(this.sceneId);
    		s.setScene(scene);
    	}
    	
    	User user=new User();
    	if (!WoUtil.isEmpty(userId)) {
			user.setId(this.userId);
			s.setUser(user);
		}
    	return s;
    }

	public StaffDto(String id, String name, String sex, Date birthday, String headImage, String mobile, String email,
			String position, String sceneId, String sceneName, String userId) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.birthday = birthday;
		this.headImage = headImage;
		this.mobile = mobile;
		this.email = email;
		this.position = position;
		this.sceneId = sceneId;
		this.sceneName = sceneName;
		this.userId = userId;
	}

	/**
	 * @param rows
	 * @param results
	 * @return
	 */
	public static WoPage<StaffDto> getGridData(List<Staff> list, Long total) {
		 List<StaffDto> ss = new ArrayList<StaffDto>();
	        for (Staff s : list) {
	            StaffDto dto = new StaffDto(s);
	            ss.add(dto);
	        }
	        WoPage<StaffDto> staDto = new WoPage<StaffDto>(ss, total);
	        return staDto;
	}

	

	
	
}
