package com.blueripple.service;
import java.io.InputStream;
import java.util.List;

import com.blueripple.controller.dto.BannerItem;
import com.blueripple.controller.dto.BannerVo;
import com.blueripple.entity.BannerEntity;

public interface BannerService {
	
	public List<BannerEntity> getNotActiveBanner();
	
	public List<BannerEntity> getActiveBanner();
	
	public String doActiveBanner(List<BannerItem> bannerItems);
	
	public String setBanner(String title ,String content ,String fileName ,String contentType ,InputStream inputStream ,String linkUrl);
	
	public List<BannerVo> getBase64(List<BannerEntity> list);
}
