package org.example.java15grup2proje.dto.request;

import org.springframework.web.multipart.MultipartFile;

public record UploadPersonnelFileRequestDto(
		MultipartFile file,
		String token,
		String personnelName,
		String personnelId,
		String fileType,
		String fileName,
		String oldFileId
) {
}