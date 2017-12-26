package com.qfedu.scene.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.format.annotation.DateTimeFormat;

import com.qfedu.common.util.WoConstant;
import com.qfedu.esys.entity.User;

/**
 * 游客
 * @author cailei
 *
 */
@Entity
@Table(name = "scene_guest")
public class Guest {
	@SuppressWarnings("unused")
	private final static Logger LOG = LogManager.getLogger(Guest.class);

	@Id
	@Column(length = 50)
	private String id;

	@Column(length = 20)
	private String name;

	@Column(length = 2)
	private String sex;

	@Column
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = WoConstant.FORMAT_DATE)
	private Date birthday;

	@Column(length = 200)
	private String headImage;

	@Column(length = 30)
	private String mobile;

	@Column(length = 50)
	private String email;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Guest [id=" + id + ", name=" + name + ", sex=" + sex + ", birthday=" + birthday + ", headImage="
				+ headImage + ", mobile=" + mobile + ", email=" + email + ", user=" + user + "]";
	}
	
	
}
