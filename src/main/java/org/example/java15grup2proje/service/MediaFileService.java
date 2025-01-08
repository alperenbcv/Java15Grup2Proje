package org.example.java15grup2proje.service;

import jakarta.validation.Valid;
import org.example.java15grup2proje.dto.request.UploadFileRequestDto;
import org.example.java15grup2proje.dto.request.UploadPersonnelFileRequestDto;
import org.example.java15grup2proje.dto.request.UploadProfilePicture;
import org.example.java15grup2proje.entity.*;
import org.example.java15grup2proje.entity.enums.EFileType;
import org.example.java15grup2proje.exception.ErrorType;
import org.example.java15grup2proje.exception.Java15Grup2ProjeAppException;
import org.example.java15grup2proje.repository.MediaFileRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MediaFileService {
	private final MediaFileRepository mediaFileRepository;
	private final CloudinaryService cloudinaryService;
	private final AuthService authService;
	private final ExpenseService expenseService;
	private final UserService userService;
	private final PersonnelFileService personnelFileService;
	
	public MediaFileService(MediaFileRepository mediaFileRepository, CloudinaryService cloudinaryService,
	                        AuthService authService, ExpenseService expenseService, UserService userService,
	                        PersonnelFileService personnelFileService) {
		this.authService = authService;
		this.mediaFileRepository = mediaFileRepository;
		this.cloudinaryService = cloudinaryService;
		this.expenseService = expenseService;
		this.userService = userService;
		this.personnelFileService = personnelFileService;
	}
	
	/*public void save(AddImageMyProductRequestDto dto) {
		Image image = ImageMapper.INSTANCE.fromAddImageProductDto(dto);
		mediaFileRepository.save(image);
	}*/
	
	public String saveImage(UploadFileRequestDto dto) throws IOException {
		authService.tokenToAuth(dto.token());
		
		MultipartFile file = dto.file();
		String expenseId = dto.expenseId();
		// Dosya boyutunu kontrol et
		
		if (file.getSize() > 2 * 1024 * 1024) {
			throw new Java15Grup2ProjeAppException(ErrorType.IMAGE_SIZE_ERROR);
		}
		
		// Dosyayı byte array'e çevir
		byte[] imageBytes = file.getBytes();
		
		// Cloudinary veya başka bir servise resmi yükle
		Map uploadResult = cloudinaryService.uploadImage(imageBytes, dto.type());
		
		// Yüklenen resmin URL'sini al
		String uploadedImageUrl = uploadResult.get("url").toString();
		Optional<Expense> optExpense = expenseService.findById(dto.expenseId());
		if (optExpense.isEmpty()) throw new Java15Grup2ProjeAppException(ErrorType.EXPENSE_NOT_FOUND);
		Expense expense = optExpense.get();
		switch (dto.type()){
			case IMAGE: expense.setImageUrl(uploadedImageUrl);
			expenseService.save(expense);
				break;
			case PDF: expense.setFileUrl(uploadedImageUrl);
				expenseService.save(expense);
				break;
			default: throw new Java15Grup2ProjeAppException(ErrorType.INVALID_FILE_TYPE);
		}
		
		MediaFile img = MediaFile.builder()
				.expenseId(expenseId)
		                       .url(uploadedImageUrl)
				.type(dto.type())
		                       .build();
		mediaFileRepository.save(img);
		return img.getUrl();
	}
	
	
	public String saveProfilePicture(UploadProfilePicture dto) throws IOException{
		User user = userService.tokenToUser(dto.token());
		
		MultipartFile file = dto.file();
		
		if (file.getSize() > 2 * 1024 * 1024) {
			throw new Java15Grup2ProjeAppException(ErrorType.IMAGE_SIZE_ERROR);
		}
		// Dosyayı byte array'e çevir
		byte[] imageBytes = file.getBytes();
		// Cloudinary veya başka bir servise resmi yükle
		Map uploadResult = cloudinaryService.uploadImage(imageBytes, EFileType.IMAGE);
		
		// Yüklenen resmin URL'sini al
		String uploadedImageUrl = uploadResult.get("url").toString();
		
		user.setPictureUrl(uploadedImageUrl);
		userService.save(user);
		return uploadedImageUrl;
	}
	
	public List<PersonnelFile> getFilesOfMyPersonnel(String token) {
		return personnelFileService.getFilesOfMyPersonnel(token);
	}
	
	public void uploadPersonnelFile(@Valid UploadPersonnelFileRequestDto dto) throws IOException {
		personnelFileService.uploadPersonnelFile(dto);
	}
}