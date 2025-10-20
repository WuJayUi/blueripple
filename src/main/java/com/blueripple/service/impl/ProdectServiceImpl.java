package com.blueripple.service.impl;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.blueripple.controller.dto.CreateProdectRequest;
import com.blueripple.entity.ImgEntity;
import com.blueripple.entity.ProdectEntity;
import com.blueripple.repository.ProdectRepository;
import com.blueripple.service.ProdectService;

@Service
public class ProdectServiceImpl implements ProdectService{
	
	@Value("${file.productImg.path}")
	public String path;
	
	@Autowired
	public ProdectRepository prodectRepository;
	
	@Override
	public String createProdect(CreateProdectRequest param, MultipartFile mainImg, List<MultipartFile> viceImgs) {
	    try {
	        // 確保目錄存在
	        File dir = new File(path);
	        if (!dir.exists()) {
	            dir.mkdirs();
	        }

	        //  主要圖片處理
	        String mainFileName = System.currentTimeMillis() + "_" + mainImg.getOriginalFilename();
	        Path mainSavePath = Paths.get(path, mainFileName);
	        Files.copy(mainImg.getInputStream(), mainSavePath, StandardCopyOption.REPLACE_EXISTING);

	        String shortId = UUID.randomUUID().toString().replace("-", "").substring(0, 10);
        
	        ProdectEntity prodect = new ProdectEntity();
	        prodect.setProdectNo(shortId); // 產生唯一商品編號
	        prodect.setProdectName(param.getProdectName());
	        prodect.setProdectDesc(param.getProdectDesc());
	        prodect.setProdectAmount(param.getProdectAmount());
	        prodect.setProdectCount(param.getProdectCount());
	        prodect.setMainImg(path + "/" + mainFileName);
	        prodect.setImgName(mainImg.getOriginalFilename());
	        prodect.setImgType(mainImg.getContentType());
            
	        //  副圖處理
	        List<ImgEntity> imgEntities = new ArrayList<>();
	        if (viceImgs != null && !viceImgs.isEmpty()) {
	            int priority = 1;
	            for (MultipartFile viceImg : viceImgs) {
	                if (!viceImg.isEmpty()) {
	                    String viceFileName = System.currentTimeMillis() + "_" + viceImg.getOriginalFilename();
	                    Path viceSavePath = Paths.get(path, viceFileName);
	                    Files.copy(viceImg.getInputStream(), viceSavePath, StandardCopyOption.REPLACE_EXISTING);

	                    ImgEntity img = new ImgEntity();
	                    img.setProdect(prodect); // 關聯商品
	                    img.setImgUrl(path + "/" + viceFileName);
	                    img.setImgName(viceImg.getOriginalFilename());
	                    img.setImgType(viceImg.getContentType());
	                    img.setPriority(priority++);
	                    imgEntities.add(img);
	                }
	            }
	        }

	        prodect.setImages(imgEntities); // 把副圖清單掛上去

	        // 存進 DB
	        prodectRepository.save(prodect);

	        return "商品建立成功，編號：" + prodect.getProdectNo();
	    } catch (Exception e) {
	        throw new RuntimeException("儲存失敗：" + e.getMessage(), e);
	    }
	}


}
