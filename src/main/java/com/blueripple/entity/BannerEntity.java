package com.blueripple.entity;

import java.io.Serializable;

import org.hibernate.annotations.Comment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "banner")
@Data
public class BannerEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column
	@Comment("標題")
	private String title;
	
	@Column
	@Comment("內容")
	private String content;
	
	@Column
	@Comment("圖片位置")
	private String imgUrl;
	
	@Column
	@Comment("圖片名稱")
	private String imgName;
	
	@Column
	@Comment("圖片樣式")
	private String imgType;
	
	@Column
	@Comment("連結位置")
	private String linkUrl;

	@Column
	@Comment("優先級")
	private Integer priority;
	
	@Column
	@Comment("是否啟用")
	private Boolean isActive;
}
