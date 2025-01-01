package org.example.java15grup2proje.mapper;

import org.example.java15grup2proje.dto.response.CommentResponseDto;
import org.example.java15grup2proje.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentMapper {
	CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);
	
}