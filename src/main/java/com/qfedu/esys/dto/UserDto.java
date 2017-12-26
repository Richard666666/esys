package com.qfedu.esys.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.qfedu.common.entity.WoPage;
import com.qfedu.common.util.WoConstant;
import com.qfedu.common.util.WoUtil;
import com.qfedu.esys.entity.Role;
import com.qfedu.esys.entity.User;

public class UserDto implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 8498380110342879997L;

    private String id;

    private String loginName;

    private String password = "123456";

    private String headImage;

    private Date createTime = new Date();

    private String roleIds = "";

    private String roleNames = "";
    
    private List<RoleDto> roles = new ArrayList<RoleDto>();

    private String staffIds = "";

    private String staffNames = "";

    private String deptIds = "";

    private String deptNames = "";

    private String staffDeptIds = "";

    private String staffDeptNames = "";

    public UserDto() {
    }

    public UserDto(User u) {
        this.id = u.getId();
        this.loginName = u.getLoginName();
        this.headImage = u.getHeadImage();
        this.password = u.getPassword();
        this.createTime = u.getCreateTime();
        for (Role r : u.getRoles()) {
            if (!"".equals(roleIds)) {
                roleIds += ",";
                roleNames += ",";
            }
            roleIds += r.getId();
            roleNames += r.getName();
            RoleDto rDto = new RoleDto();
            rDto.setId(r.getId());
            rDto.setName(r.getName());
            roles.add(rDto);
        }
    }

    /**
     * 创建PO，设置简单属性和角色关系数据
     * @return
     */
    public User createEntity () {
    	User u = new User();
    	// 设置简单属性
    	u.setCreateTime(this.createTime);
    	u.setHeadImage(headImage);
    	u.setId(id);
    	u.setLoginName(loginName);
    	u.setPassword(password);
    	// 设置关系数据
    	List<Role> roles = new ArrayList<Role>();
		for (String id : getRoleIdArray()) {
			Role r = new Role ();
			r.setId(id);
			roles.add(r);
		}
		u.setRoles(roles);
    	return u;
    }
    
    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    public String getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(String roleNames) {
        this.roleNames = roleNames;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    @JsonFormat(pattern = WoConstant.FORMAT_DATETIME, locale = "zh", timezone = "GMT+8")
    public Date getCreateTime() {
        return createTime;
    }

    @DateTimeFormat(pattern = WoConstant.FORMAT_DATETIME)
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStaffIds() {
        return staffIds;
    }

    public void setStaffIds(String staffIds) {
        this.staffIds = staffIds;
    }

    public String getStaffNames() {
        return staffNames;
    }

    public void setStaffNames(String staffNames) {
        this.staffNames = staffNames;
    }

    public String getDeptIds() {
        return deptIds;
    }

    public void setDeptIds(String deptIds) {
        this.deptIds = deptIds;
    }

    public String getDeptNames() {
        return deptNames;
    }

    public void setDeptNames(String deptNames) {
        this.deptNames = deptNames;
    }

    public String getStaffDeptIds() {
        return staffDeptIds;
    }

    public void setStaffDeptIds(String staffDeptIds) {
        this.staffDeptIds = staffDeptIds;
    }

    public String getStaffDeptNames() {
        return staffDeptNames;
    }

    public void setStaffDeptNames(String staffDeptNames) {
        this.staffDeptNames = staffDeptNames;
    }

    
    public List<RoleDto> getRoles() {
		return roles;
	}

	public void setRoles(String roleIds) {
		this.roleIds = roleIds;
	}

	public String[] getRoleIdArray () {
		return WoUtil.splitIds(this.roleIds);
	}
	
	/**
     * @return
     */
    public boolean isAdmin() {
        return "admin".equals(this.getId());
    }
    
    /**
     * @return
     */
    public boolean hasStaff () {
    	return !WoUtil.isEmpty(this.staffIds);
    }

    /**
     * @param list
     * @return
     */
    public static WoPage<UserDto> getGridData(List<User> list, Long total) {
        List<UserDto> us = new ArrayList<UserDto>();
        for (User u : list) {
            UserDto dto = new UserDto(u);
            us.add(dto);
        }
        WoPage<UserDto> puDto = new WoPage<UserDto>(us, total);
        return puDto;
    }
}
