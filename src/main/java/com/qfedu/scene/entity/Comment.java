package com.qfedu.scene.entity;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.format.annotation.DateTimeFormat;

import com.qfedu.common.util.WoConstant;


/**
 * 评论
 * @author chenxuewen
 *
 */
@Entity
@Table(name="scene_comment")
public class Comment {
	@SuppressWarnings("unused")
	private final static Logger LOG = LogManager.getLogger(Comment.class);
	
	/**
	 * ID主键
	 */
	@Id
	@Column(length=50)
	private String id;
	
	/**
	 * 标题
	 */
	@Column(length = 50)
	private String title;
	
	/**
	 * 内容
	 */
	@Column(length = 200)
	private String content;
	
	/**
	 * 创建时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = WoConstant.FORMAT_DATE)
	private Date createTime;
	
	/**
	 * 关联游客，多对一
	 */
	@ManyToOne
	@JoinColumn(name = "guest_id")
	private Guest guest;
	
	/**
	 * 关联景区，多对一
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}
	
	
	
}
