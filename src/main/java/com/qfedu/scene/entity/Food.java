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
 * 景区内部的美食
 * @author cailei
 *
 */
@Entity
@Table(name="scene_food")
public class Food {
	@SuppressWarnings("unused")
	private final static Logger LOG = LogManager.getLogger(Food.class);

	@Id
	@Column(length=50)
	private String id;
	
	@Column(length=50)
	private String name;
	
	@Column(length=50)
	private String seller;
	
	@Column(length=50)
	private Double price;
	
	@Column(length=50)
	private String detail;
	
	@Column(length=50)
	private String address;

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

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	
}
