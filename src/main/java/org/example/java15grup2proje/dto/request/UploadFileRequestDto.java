package org.example.java15grup2proje.dto.request;

import org.example.java15grup2proje.entity.enums.EFileType;
import org.springframework.web.multipart.MultipartFile;

public record UploadFileRequestDto(
		MultipartFile file,
		String expenseId,
		String token,
		EFileType type
) {
}