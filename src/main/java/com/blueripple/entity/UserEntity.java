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
@Table(name = "users")
@Data
public class UserEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column
    @Comment("使用者名稱")
    private String userName;   

    @Column
    @Comment("使用者密碼")
    private String userPassword;   

    @Column
    @Comment("生日")
    private String birthDay;   

    @Column
    @Comment("電話號碼")
    private String phoneNumber;   

    @Column
    @Comment("地址")
    private String adress;   
	
}
