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
 * 门票类型
 * @author cailei
 *
 */
@Entity
@Table(name = "scene_ticket") 
public class Ticket {
	@SuppressWarnings("unused")
	private final static Logger LOG = LogManager.getLogger(Ticket.class);

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
	 * 价格
	 */
	@Column
	private Double price;
	
	/**
	 * 库存
	 */
	@Column
	private Double stock;
	
	/**
	 * 关联景区 多对一
	 */
	@ManyToOne
	@JoinColumn(name = "scene_id")
	private Scene scene;

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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getStock() {
		return stock;
	}

	public void setStock(Double stock) {
		this.stock = stock;
	}

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}
	
}
