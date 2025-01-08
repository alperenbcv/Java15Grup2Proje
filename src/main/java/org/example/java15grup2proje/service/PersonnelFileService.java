package org.example.java15grup2proje.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.java15grup2proje.dto.request.UploadPersonnelFileRequestDto;
import org.example.java15grup2proje.entity.Auth;
import org.example.java15grup2proje.entity.Employee;
import org.example.java15grup2proje.entity.PersonnelFile;
import org.example.java15grup2proje.entity.enums.EFileType;
import org.example.java15grup2proje.entity.enums.ERole;
import org.example.java15grup2proje.exception.ErrorType;
import org.example.java15grup2proje.exception.Java15Grup2ProjeAppException;
import org.example.java15grup2proje.mapper.PersonnelFileMapper;
import org.example.java15grup2proje.repository.PersonnelFileRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonnelFileService {
	private final PersonnelFileRepository personnelFileRepository;
	private final AuthService authService;
	private final EmployeeService employeeService;
	private final CloudinaryService cloudinaryService;
	
	public List<PersonnelFile> getFilesOfMyPersonnel(String token) {
		Auth auth = authService.tokenToAuth(token);
		if (auth.getRole() != ERole.MANAGER) throw new Java15Grup2ProjeAppException(ErrorType.ROLE_EXCEPTION);
		List<Employee> employees = employeeService.findAllByManagerId(auth.getId());
		List<String> employeeIdList = employees.stream().map(employee -> employee.getId()).toList();
		return personnelFileRepository.findAllByPersonnelIdInAndState(employeeIdList, 1);
	}
	
	public void uploadPersonnelFile(@Valid UploadPersonnelFileRequestDto dto) throws IOException {
		Auth auth =authService.tokenToAuth(dto.token());
		if (auth.getRole() != ERole.MANAGER) throw new Java15Grup2ProjeAppException(ErrorType.ROLE_EXCEPTION);
		Optional<PersonnelFile> optFile = personnelFileRepository.findById(dto.oldFileId());
		if(optFile.isEmpty()) throw new Java15Grup2ProjeAppException(ErrorType.FILE_NOT_FOUND);
		optFile.get().setState(0);
		
		MultipartFile file = dto.file();
		// Dosya boyutunu kontrol et
		
		if (file.getSize() > 2 * 1024 * 1024) {
			throw new Java15Grup2ProjeAppException(ErrorType.IMAGE_SIZE_ERROR);
		}
		
		// Dosyayı byte array'e çevir
		byte[] imageBytes = file.getBytes();
		
		// Cloudinary veya başka bir servise resmi yükle
		Map uploadResult = cloudinaryService.uploadImage(imageBytes, EFileType.PDF);
		
		// Yüklenen resmin URL'sini al
		String uploadedImageUrl = uploadResult.get("url").toString();
		
		PersonnelFile personnelFile = PersonnelFileMapper.INSTANCE.fromUploadDtoToFile(dto, uploadedImageUrl);
		personnelFileRepository.save(personnelFile);
		personnelFileRepository.save(optFile.get());
	}
}