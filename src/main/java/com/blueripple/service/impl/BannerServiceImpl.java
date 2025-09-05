package com.blueripple.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Value;
import com.blueripple.controller.dto.BannerItem;
import com.blueripple.controller.dto.BannerVo;
import com.blueripple.entity.BannerEntity;
import com.blueripple.repository.BannerRepository;
import com.blueripple.service.BannerService;


@Service
public class BannerServiceImpl implements BannerService{
	
	@Value("${file.path}")
	public String path;

	@Autowired
	private BannerRepository bannerRepository;
	
	@Override
	public List<BannerEntity> getNotActiveBanner() {
		return bannerRepository.findByIsActiveFalseOrderByPriorityAsc();
	}

	@Transactional
	@Override
	public String doActiveBanner(List<BannerItem> bannerItems) {

	    // 找出目前已啟用的 Banner
	    List<BannerEntity> activeBanners = bannerRepository.findByIsActiveTrueOrderByPriorityAsc();

	    // 取出這次前端送來要上架的 ID
	    List<Long> newIds = bannerItems.stream()
	            .map(BannerItem::getId)
	            .collect(Collectors.toList());

	    // 1. 把不在新清單裡的舊 Banner 下架
	    activeBanners.stream()
	            .filter(b -> !newIds.contains(b.getId()))
	            .forEach(b -> b.setIsActive(false));

	    // 2. 更新前端送來要上架的 Banner（順便設定 priority）
	    List<BannerEntity> bannersToSave = bannerItems.stream().map(item -> {
	        BannerEntity banner = bannerRepository.findById(item.getId())
	                .orElseThrow(() -> new RuntimeException("未找到此告示 ID=" + item.getId()));
	        banner.setIsActive(true);
	        banner.setPriority(item.getPriority());
	        return banner;
	    }).collect(Collectors.toList());

	    // 3. 合併需要更新的 Banner 一次存檔
	    List<BannerEntity> allToSave = new ArrayList<>();
	    allToSave.addAll(activeBanners);  // 已經被改成 false 的
	    allToSave.addAll(bannersToSave);  // 要上架的 + priority

	    bannerRepository.saveAll(allToSave);

	    return "OK";
	}




	@Override
	public List<BannerEntity> getActiveBanner() {
		return bannerRepository.findByIsActiveTrueOrderByPriorityAsc();
	}
	
	 @Override
	    public String setBanner(String title,
	                            String content,
	                            String fileName,
	                            String contentType,
	                            InputStream inputStream,
	                            String linkUrl) {
	        try {
	            // 確保目錄存在
	            File dir = new File(path);
	            if (!dir.exists()) {
	                dir.mkdirs();
	            }

	            // 生成唯一檔名（避免重複）
	            String uniqueFileName = System.currentTimeMillis() + "_" + fileName;

	            // 檔案存放路徑
	            Path savePath = Paths.get(path, uniqueFileName);

	            // 儲存檔案
	            Files.copy(inputStream, savePath, StandardCopyOption.REPLACE_EXISTING);

	            // 建立 DB entity
	            BannerEntity banner = new BannerEntity();
	            banner.setTitle(title);
	            banner.setContent(content);
	            // 存入 DB 的路徑改為 URL 友好的形式 (一定用 `/`)
	            banner.setImgUrl("uploads/" + uniqueFileName);
	            banner.setImgName(fileName); 
	            banner.setImgType(contentType); 
	            banner.setLinkUrl(linkUrl);
	            banner.setIsActive(false);             // 預設不啟用

	            bannerRepository.save(banner);

	            return "上傳成功：" + uniqueFileName;
	        } catch (Exception e) {
	            throw new RuntimeException("儲存失敗：" + e.getMessage(), e);
	        }
	    }

	@Override
	public List<BannerVo> getBase64(List<BannerEntity> list) {
		 // 把圖片內容轉成 Base64
    	List<BannerVo> banners = new ArrayList<>();
        for (BannerEntity banner : list) {
        	String base64Img = null;
            try {
                Path imgPath = Paths.get(banner.getImgUrl()); // 這裡是 uploads/xxx.jpg
                byte[] fileBytes = Files.readAllBytes(imgPath);
             // 這裡組 Data URL
                base64Img = "data:" + banner.getImgType() + ";base64," + Base64.getEncoder().encodeToString(fileBytes);


            } catch (IOException e) {
            	base64Img = null;
            }
            // Entity → VO
            BannerVo vo = new BannerVo(
            		banner.getId(),
                    banner.getTitle(),
                    banner.getContent(),
                    base64Img,
                    banner.getImgName(),
                    banner.getImgType(),
                    banner.getLinkUrl(),
                    banner.getPriority(),
                    banner.getIsActive()
            );
            banners.add(vo);
        }
        
        return banners;
	}

	
}
