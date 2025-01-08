package org.example.java15grup2proje.dto.request;

import org.springframework.web.multipart.MultipartFile;

public record UploadProfilePicture(
		MultipartFile file,
		String token
		) {
}