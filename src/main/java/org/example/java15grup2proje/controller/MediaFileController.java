package org.example.java15grup2proje.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.java15grup2proje.dto.request.UploadFileRequestDto;
import org.example.java15grup2proje.dto.request.UploadPersonnelFileRequestDto;
import org.example.java15grup2proje.dto.request.UploadProfilePicture;
import org.example.java15grup2proje.dto.response.BaseResponse;
import org.example.java15grup2proje.entity.MediaFile;
import org.example.java15grup2proje.entity.PersonnelFile;
import org.example.java15grup2proje.service.CloudinaryService;
import org.example.java15grup2proje.service.MediaFileService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import static org.example.java15grup2proje.constant.RestApi.*;

import java.awt.*;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(MEDIA_FILE)
@CrossOrigin("*")
public class MediaFileController {
	private final MediaFileService mediaFileService;
	private final CloudinaryService cloudinaryService;
	
	
	@PostMapping(value = UPLOAD_MEDIA, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<BaseResponse<Boolean>> uploadExpenseMedia(UploadFileRequestDto dto) throws IOException {
		
		String uploadedImageUrl = mediaFileService.saveImage(dto);
		
		return ResponseEntity.ok(BaseResponse.<Boolean>builder()
				                         .data(true)
				                         .message("dosya başarıyla yüklendi")
				                         .code(200)
				                         .success(true)
		                                     .build());
		
	}
	
	@PostMapping(value = UPLOAD_PROFILE_PICTURE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<BaseResponse<Boolean>> uploadProfilePicture(UploadProfilePicture dto) throws IOException {
		
		String uploadedImageUrl = mediaFileService.saveProfilePicture(dto);
		
		return ResponseEntity.ok(BaseResponse.<Boolean>builder()
		                                     .data(true)
		                                     .message("dosya başarıyla yüklendi")
		                                     .code(200)
		                                     .success(true)
		                                     .build());
		
	}
	
	@GetMapping(GET_FILES_OF_MY_PERSONNEL)
	public ResponseEntity<BaseResponse<java.util.List<PersonnelFile>>> getFilesOfMyPersonnel(String token){
		return ResponseEntity.ok(BaseResponse.<List<PersonnelFile>>builder()
		                                     .data(mediaFileService.getFilesOfMyPersonnel(token))
		                                     .message("personnel file başarıyla getirildi")
		                                     .code(200)
		                                     .success(true)
		                                     .build());
	}
	
	@PostMapping(value= UPLOAD_PERSONNEL_FILE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<BaseResponse<Boolean>> addPersonnelFile(UploadPersonnelFileRequestDto dto) throws
	                                                                                                                     IOException {
		mediaFileService.uploadPersonnelFile(dto);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder()
		                                     .data(true)
		                                     .success(true)
		                                     .code(200)
		                                     .message("personnel file successfully uploaded")
		                                     .build());
	}
	
}