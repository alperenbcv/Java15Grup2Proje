package org.example.java15grup2proje.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.java15grup2proje.dto.request.DeleteEmployeeRequestDto;
import org.example.java15grup2proje.dto.request.EmployeeActivationRequestDto;
import org.example.java15grup2proje.dto.request.EmployeeRegisterRequestDto;
import org.example.java15grup2proje.dto.response.EmployeeListDto;
import org.example.java15grup2proje.exception.ErrorType;
import org.example.java15grup2proje.exception.Java15Grup2ProjeAppException;
import org.example.java15grup2proje.dto.request.LoginRequestDto;
import org.example.java15grup2proje.entity.Employee;
import org.example.java15grup2proje.entity.enums.ERole;
import org.example.java15grup2proje.model.EmployeeNameSurname;
import org.example.java15grup2proje.repository.EmployeeRepository;
import org.example.java15grup2proje.utility.JwtManager;
import org.example.java15grup2proje.utility.PasswordHasher;
import org.example.java15grup2proje.mapper.EmployeeMapper;
import org.example.java15grup2proje.utility.TimeConverter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {
	private final JwtManager jwtManager;
	private final EmployeeRepository employeeRepository;
	private final ManagerHelperService managerHelperService;
	private final MailService mailService;
	private final PasswordHasher passwordHasher;
	
	public void employeeRegister(@Valid EmployeeRegisterRequestDto dto){
		Optional<String> optManagerId = jwtManager.validateToken(dto.token());
		if(optManagerId.isEmpty()){
			throw new Java15Grup2ProjeAppException(ErrorType.USER_NOT_FOUND);
		}
		String managerId = optManagerId.get();
		String companyId = managerHelperService.getCompanyIdByManagerId(managerId);
		Employee employee = EmployeeMapper.INSTANCE.fromRegisterRequestDto(dto);
		employee.setCompanyId(companyId);
		employee.setRole(ERole.EMPLOYEE);
		String activationToken = jwtManager.createActivationToken();
		employee.setActivationToken(activationToken);
		employee.setManagerId(managerId);
		employeeRepository.save(employee);
		mailService.sendEmployeeActivationMail(dto.email(), activationToken);
	}
	
	public void employeeActivateAccount(@Valid EmployeeActivationRequestDto employeeActivationRequestDto){
		if (!jwtManager.validateActivationToken(employeeActivationRequestDto.activationToken())) {
			throw new Java15Grup2ProjeAppException(ErrorType.ACTIVATON_TOKEN_EXPIRED);
		}
		Employee employee=
				employeeRepository.findEmployeeByActivationToken(employeeActivationRequestDto.activationToken())
		                 .orElseThrow(() -> new Java15Grup2ProjeAppException(ErrorType.USER_NOT_FOUND));
		if (employee.isAccountActive()) {
			throw new Java15Grup2ProjeAppException(ErrorType.ACCOUNT_ALREADY_ACTIVE);
		}
		employee.setActivationToken(null);
		employee.setAccountActive(true);
		employee.setAccountVerified(true);
		employee.setGender(employeeActivationRequestDto.gender());
		employee.setTitle(employeeActivationRequestDto.title());
		employee.setPassword(PasswordHasher.passwordHash(employeeActivationRequestDto.password()));
		employee.setBirthDate(TimeConverter.localDateToEpoch(employeeActivationRequestDto.birthDate()));
		employeeRepository.save(employee);
		
	}
	
	public String employeeLogin(@Valid LoginRequestDto dto) {
		Optional<Employee> optionalEmployee = employeeRepository.findEmployeeByEmail(dto.email());
		if (optionalEmployee.isEmpty()) {
			throw new Java15Grup2ProjeAppException(ErrorType.INVALID_MAIL_OR_PASSWORD);
		}
		boolean passwordMatches = PasswordHasher.compareHashedPassword(dto.password(), optionalEmployee.get().getPassword());
		if (!passwordMatches) {
			throw new Java15Grup2ProjeAppException(ErrorType.INVALID_MAIL_OR_PASSWORD);
		}
		String token = jwtManager.createToken(optionalEmployee.get().getId(),optionalEmployee.get().getRole().toString());
		return token;
	}
	
	public List<EmployeeListDto> getEmployeeListDto(String companyId){
		return employeeRepository.findEmployeeListDtoByCompany(companyId);
	}
	
	public void deleteEmployee(DeleteEmployeeRequestDto dto){
		Optional<String> optManagerId = jwtManager.validateToken(dto.token());
		if(optManagerId.isEmpty()){
			throw new Java15Grup2ProjeAppException(ErrorType.USER_NOT_FOUND);
		}
		Optional<Employee> optionalEmployee = employeeRepository.findEmployeeByEmail(dto.mail());
		if(optionalEmployee.isEmpty())
			throw new Java15Grup2ProjeAppException(ErrorType.USER_NOT_FOUND);
		Employee employee = optionalEmployee.get();
		employee.setState(0);
		employee.setAccountActive(false);
		employeeRepository.save(employee);
	}
	
	public List<Employee> getEmployeeByMail(List<String> mailList){
		return employeeRepository.findEmployeeByEmailIn(mailList);
	}
	
	public List<String> getEmployeeNameSurnameById(List<String> id){
		List<EmployeeNameSurname> employeeModelList =
				employeeRepository.findEmployeeNameSurnameByIdIn(id);
		List<String> employeeModel = new ArrayList<>();
		for (EmployeeNameSurname employeeNameSurname:employeeModelList){
			StringBuilder stringBuilder = new StringBuilder(employeeNameSurname.name()+" "+employeeNameSurname.surname());
			employeeModel.add(stringBuilder.toString());
		}
		
		return employeeModel;
	}
	
	public boolean existsById(String id){
		return employeeRepository.existsById(id);
	}
	
	public Employee findById (String id){
		Optional<Employee> byId = employeeRepository.findById(id);
		if(byId.isEmpty())
			throw new Java15Grup2ProjeAppException(ErrorType.USER_NOT_FOUND);
		return byId.get();
	}
}