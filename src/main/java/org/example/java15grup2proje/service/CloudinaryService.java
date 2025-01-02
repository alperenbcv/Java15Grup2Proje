package org.example.java15grup2proje.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.example.java15grup2proje.entity.enums.EFileType;
import org.example.java15grup2proje.exception.ErrorType;
import org.example.java15grup2proje.exception.Java15Grup2ProjeAppException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {
	private final Cloudinary cloudinary;
	
	public CloudinaryService(@Value("${cloudinary.cloud_name}") String cloudName,
	                         @Value("${cloudinary.api_key}") String apiKey,
	                         @Value("${cloudinary.api_secret}") String apiSecret) {
		this.cloudinary = new Cloudinary(ObjectUtils.asMap(
				"cloud_name", cloudName,
				"api_key", apiKey,
				"api_secret", apiSecret));
	}
	
	public Map uploadImage(byte[] imageBytes, EFileType type) throws IOException {
		return switch (type){
			case IMAGE -> cloudinary.uploader().upload(imageBytes,  ObjectUtils.asMap("resource_type", "image"));
			case PDF ->  cloudinary.uploader().upload(imageBytes,  ObjectUtils.asMap("resource_type", "raw"));
			default -> throw new Java15Grup2ProjeAppException(ErrorType.INVALID_FILE_TYPE);
		};
		
	}
}