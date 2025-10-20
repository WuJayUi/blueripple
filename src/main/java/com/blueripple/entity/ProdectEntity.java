package com.blueripple.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Comment;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "prodectEntity")
public class ProdectEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column
	@Comment("產品編號")
	private String prodectNo;
	
	@Column
	@Comment("產品名稱")
	private String prodectName;
	
	@Column
	@Comment("產品描述")
	private String prodectDesc;
	
	@Column
	@Comment("產品數量")
	private String prodectCount;
	
	@Column
	@Comment("產品金額")
	private String prodectAmount;
	
	@Column
	@Comment("產品主圖")
	private String mainImg;
	
	@Column
	@Comment("主圖名稱")
	private String imgName;
	
	@Column
	@Comment("主圖樣式")
	private String imgType;	
	
    //  關聯到 ImgEntity
    @OneToMany(mappedBy = "prodect", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ImgEntity> images = new ArrayList<>();
}
