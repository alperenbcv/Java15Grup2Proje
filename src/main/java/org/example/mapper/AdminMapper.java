package org.example.mapper;

import org.example.java15grup2proje.dto.request.AdminRegisterRequestDto;
import org.example.java15grup2proje.entity.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AdminMapper {
	AdminMapper INSTANCE = Mappers.getMapper(AdminMapper.class);
	
	@Mapping(target = "password", expression = "java(org.example.java15grup2proje.utility.PasswordHasher.passwordHash(dto.password()))")
	Admin fromAdminRegisterDto(AdminRegisterRequestDto dto);
}