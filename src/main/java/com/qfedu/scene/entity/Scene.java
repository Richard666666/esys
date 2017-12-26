package com.qfedu.scene.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * 景区
 * @author cailei
 *
 */
@Entity
@Table(name = "scene_info")
public class Scene {
	@SuppressWarnings("unused")
	private final static Logger LOG = LogManager.getLogger(Scene.class);

	/**
	 * Id
	 */
	@Id
	@Column(length = 50)
	private String id;
	
	/**
	 * 名称
	 */
	@Column(length = 50)
	private String name;
	
	/**
	 * 开放时间
	 */
	@Column(length = 200)
	private String open;
	
	/**
	 * 地点
	 */
	@Column(length = 200)
	private String address;
	
	/**
	 * 详情
	 */
	@Column(length = 200)
	private String detail;

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

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	
}



