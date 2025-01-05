package org.example.java15grup2proje.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.java15grup2proje.dto.request.UploadPersonnelFileRequestDto;
import org.example.java15grup2proje.dto.response.BaseResponse;
import org.example.java15grup2proje.entity.PersonnelFile;
import org.example.java15grup2proje.service.PersonnelFileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static org.example.java15grup2proje.constant.RestApi.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(PERSONNEL_FILE)
@CrossOrigin("*")
public class PersonnelFileController {
	private final PersonnelFileService personnelFileService;
	
	@GetMapping(GET_FILES_OF_MY_PERSONNEL)
	public ResponseEntity<BaseResponse<List<PersonnelFile>>> getFilesOfMyPersonnel(String token){
		return ResponseEntity.ok(BaseResponse.<List<PersonnelFile>>builder()
		                                     .data(personnelFileService.getFilesOfMyPersonnel(token))
		                                     .message("personnel file başarıyla getirildi")
		                                     .code(200)
		                                     .success(true)
		                                     .build());
	}
	
	@PostMapping(UPLOAD_PERSONNEL_FILE)
	public ResponseEntity<BaseResponse<Boolean>> addPersonnelFile(@RequestBody @Valid UploadPersonnelFileRequestDto dto) throws
	                                                                                                                     IOException {
		personnelFileService.uploadPersonnelFile(dto);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder()
				                         .data(true)
				                         .success(true)
				                         .code(200)
				                         .message("personnel file successfully uploaded")
		                                     .build());
	}
}