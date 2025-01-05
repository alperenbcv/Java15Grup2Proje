package org.example.java15grup2proje.mapper;

import org.example.java15grup2proje.dto.request.UploadPersonnelFileRequestDto;
import org.example.java15grup2proje.entity.PersonnelFile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface PersonnelFileMapper {
	public static PersonnelFileMapper INSTANCE = Mappers.getMapper(PersonnelFileMapper.class);
	PersonnelFile fromUploadDtoToFile(UploadPersonnelFileRequestDto dto, String fileUrl);
}