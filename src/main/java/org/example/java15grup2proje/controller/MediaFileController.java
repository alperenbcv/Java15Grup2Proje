package org.example.java15grup2proje.controller;

import lombok.RequiredArgsConstructor;
import org.example.java15grup2proje.dto.request.UploadFileRequestDto;
import org.example.java15grup2proje.dto.response.BaseResponse;
import org.example.java15grup2proje.entity.MediaFile;
import org.example.java15grup2proje.service.CloudinaryService;
import org.example.java15grup2proje.service.MediaFileService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import static org.example.java15grup2proje.constant.RestApi.*;

import java.awt.*;
import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping(MEDIA_FILE)
@CrossOrigin("*")
public class MediaFileController {
	private final MediaFileService mediaFileService;
	private final CloudinaryService cloudinaryService;
	
	
	@PostMapping(value = UPLOAD_MEDIA, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<BaseResponse<Boolean>> uploadPhotoByUrl(UploadFileRequestDto dto) throws IOException {
		
		String uploadedImageUrl = mediaFileService.saveImage(dto);
		
		return ResponseEntity.ok(BaseResponse.<Boolean>builder()
				                         .data(true)
				                         .message("dosya başarıyla yüklendi")
				                         .code(200)
				                         .success(true)
		                                     .build());
		
	}
	
	
}