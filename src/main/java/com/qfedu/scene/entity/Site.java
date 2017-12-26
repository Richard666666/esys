package com.qfedu.scene.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * 景点
 * @author cailei
 *
 */
@Entity
@Table(name="scene_site")
public class Site {
	@SuppressWarnings("unused")
	private final static Logger LOG = LogManager.getLogger(Site.class);

	@Id
	@Column(length=50)
	private String id;

	@Column(length=50)
	private String name;
	
	@Column(length=200)
	private String open;
	
	@Column(length=200)
	private String address;
	
	@Column(length=200)
	private String detail;

	@ManyToOne
	@JoinColumn(name="scene_id")
	private Scene scene;
	
	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
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
