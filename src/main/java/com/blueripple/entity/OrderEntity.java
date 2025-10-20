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
@Data
@Table(name = "orders")
public class OrderEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column
	@Comment("訂單編號")
	private String orderNo;
	
	@Column
	@Comment("會員編號")
	private String menberNo;
	
	@Column
	@Comment("訂單金額")
	private String orderAmount;
	
	@Column
	@Comment("訂單狀態")
	private String orderStatus;
	
}
