package org.example.mapper;

import org.example.java15grup2proje.dto.request.AdminRegisterRequestDto;
import org.example.java15grup2proje.dto.request.CompanyRegisterRequestDto;
import org.example.java15grup2proje.entity.Admin;
import org.example.java15grup2proje.entity.Company;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CompanyMapper {
	CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);
	
	@Mapping(target = "establishedDate", expression = "java(org.example.java15grup2proje.utility.TimeConverter" +
			".localDateToEpoch(dto.establishedDate()))")
	Company fromCompanyRegisterRequestDto(CompanyRegisterRequestDto dto);
}