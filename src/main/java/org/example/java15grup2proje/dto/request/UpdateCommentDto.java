package org.example.java15grup2proje.dto.request;

public record UpdateCommentDto(
		String token,
		String commentId,
		String comment
) {
}