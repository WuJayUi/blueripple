package com.blueripple.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;
import io.swagger.v3.oas.annotations.media.Content;
import com.blueripple.controller.dto.BannerVo;
import com.blueripple.controller.dto.DoActiveBannerRequest;
import com.blueripple.controller.dto.GetAllBannerResponse;
import com.blueripple.entity.BannerEntity;
import com.blueripple.service.BannerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/back")
@CrossOrigin(origins = "http://localhost:3000") // 允许跨域
public class BackController {
	
	@Autowired
	private BannerService bannerService;

    @Operation(summary = "上傳告示")
    @PostMapping(value = "/uploadBanner", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadBanner(
            @Parameter(description = "標題", required = true)
            @RequestParam("title") String title,

            @Parameter(description = "內容", required = true)
            @RequestParam("content") String content,

            @Parameter(description = "連結")
            @RequestParam(value = "linkUrl", required = false) String linkUrl,

            @Parameter(description = "上傳檔案", required = true,
                       content = @Content(mediaType = MediaType.APPLICATION_OCTET_STREAM_VALUE))
            @RequestPart("file") MultipartFile file) {
		
		String result =null;
		
		try {
			result = bannerService.setBanner(title, content, file.getOriginalFilename() ,file.getContentType() ,file.getInputStream() ,linkUrl);
			return ResponseEntity.ok(result);
		}catch(Exception e){
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("上傳失敗：" + e.getMessage());
		}

	}
    
    @Operation(summary = "設定上架告示")
    @PostMapping(value = "/doActiveBanner")
    public ResponseEntity<String> doActiveBanner(@RequestBody DoActiveBannerRequest request){
    	String result = bannerService.doActiveBanner(request.getBanners());
    	return ResponseEntity.ok(result);
    }
    
    @Operation(summary = "取得上架告示")
    @PostMapping(value = "/getActiveBanner")
    public ResponseEntity<GetAllBannerResponse> getActiveBanner(){
    	List<BannerEntity> list = bannerService.getActiveBanner();
    	List<BannerVo> banners = bannerService.getBase64(list);
    	GetAllBannerResponse result = new GetAllBannerResponse();
    	result.setBanner(banners);
    	return ResponseEntity.ok(result);
    }
    
    @Operation(summary = "取得所有未啟動告示")
    @PostMapping(value = "/getNotActiveBanner")
    public ResponseEntity<GetAllBannerResponse> getNotActiveBanner(){
    	List<BannerEntity> list = bannerService.getNotActiveBanner();
    	List<BannerVo> banners = bannerService.getBase64(list);
    	GetAllBannerResponse result = new GetAllBannerResponse();
    	result.setBanner(banners);
    	return ResponseEntity.ok(result);
    }

}
