package org.example.java15grup2proje.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.java15grup2proje.entity.Comment;

@Getter
@Setter
@Builder
public class CommentResponseDto {
	String companyName;
	String companyLogoUrl;
	String managerName;
	Comment comment;
}