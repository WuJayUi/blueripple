package com.blueripple.entity;

import java.io.Serializable;
import org.hibernate.annotations.Comment;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "img")
@Data
public class ImgEntity  implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
		

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prodect_id", nullable = false) // 外鍵
    private ProdectEntity prodect;
	
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
	@Comment("優先級")
	private Integer priority;
	
}
