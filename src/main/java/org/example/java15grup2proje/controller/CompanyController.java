package org.example.java15grup2proje.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.java15grup2proje.dto.request.AdminRegisterRequestDto;
import org.example.java15grup2proje.dto.request.CompanyRegisterRequestDto;
import org.example.java15grup2proje.dto.request.ManageCompanyRegisterStateRequestDto;
import org.example.java15grup2proje.dto.response.BaseResponse;
import org.example.java15grup2proje.entity.Company;
import org.example.java15grup2proje.service.CompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.example.java15grup2proje.constant.RestApi.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(AUTH_COMPANY)
@CrossOrigin("*")
public class CompanyController {
	private final CompanyService companyService;
	
	@PostMapping(REGISTER)
	public ResponseEntity<BaseResponse<Boolean>> register(@RequestBody @Valid CompanyRegisterRequestDto dto){
		companyService.companyRegister(dto);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder().code(200).data(true).message("Company register successful.").success(true).build());
	}
	
	@GetMapping(GET_ALL_COMPANY_NAMES)
	public ResponseEntity<BaseResponse<List<String>>> getAllCompanyNames(){
		return ResponseEntity.ok(BaseResponse.<List<String>>builder().code(200)
		                                     .data(companyService.getAllCompanyNames()).message("All company names brought").success(true).build());
		
	}
	
	@GetMapping(GET_ALL_COMPANIES)
	public ResponseEntity<BaseResponse<List<Company>>> getAllCompanies(){
		return ResponseEntity.ok(BaseResponse.<List<Company>>builder().code(200)
		                                     .data(companyService.getAllCompanies()).message("All company names brought").success(true).build());
		
	}
	
	@PostMapping(MANAGE_COMPANY_REGISTER_STATE)
	public ResponseEntity<BaseResponse<Boolean>> manageCompanyRegisterState(@RequestBody @Valid ManageCompanyRegisterStateRequestDto dto){
		companyService.manageCompanyRegisterState(dto);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder().code(200)
		                                     .data(true).message("All company names brought").success(true).build());
		
	}
	
}