package com.blueripple.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.blueripple.controller.dto.CreateProdectRequest;

public interface ProdectService {
	
	public String createProdect(CreateProdectRequest param, MultipartFile mainImg, List<MultipartFile> viceImgs);

}
