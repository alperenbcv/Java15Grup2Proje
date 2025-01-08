package org.example.java15grup2proje.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.java15grup2proje.dto.request.EmployeeActivationRequestDto;
import org.example.java15grup2proje.dto.request.EmployeeRegisterRequestDto;
import org.example.java15grup2proje.entity.Manager;
import org.example.java15grup2proje.exception.ErrorType;
import org.example.java15grup2proje.exception.Java15Grup2ProjeAppException;
import org.example.java15grup2proje.entity.Employee;
import org.example.java15grup2proje.entity.enums.ERole;
import org.example.java15grup2proje.model.EmployeeNameSurname;
import org.example.java15grup2proje.repository.EmployeeRepository;
import org.example.java15grup2proje.repository.UserRepository;
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
	private final ManagerService managerService;
	private final UserRepository userRepository;
	private final MailService mailService;
	private final ManagerHelperService managerHelperService;
	
	public void employeeRegister(@Valid EmployeeRegisterRequestDto dto){
		Optional<String> optManagerId = jwtManager.validateToken(dto.token());
		if(optManagerId.isEmpty()){
			throw new Java15Grup2ProjeAppException(ErrorType.NOT_FOUND_USER);
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
			throw new Java15Grup2ProjeAppException(ErrorType.ACTIVATION_TOKEN_EXPIRED);
		}
		Employee employee=
				employeeRepository.findEmployeeByActivationToken(employeeActivationRequestDto.activationToken())
				                  .orElseThrow(() -> new Java15Grup2ProjeAppException(ErrorType.NOT_FOUND_USER));
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
	
	public List<Employee> getPersonnelByCompanyId(String token) {
		Manager manager = managerService.tokenToManager(token);
		String companyId = manager.getCompanyId();
		return employeeRepository.findAllByCompanyId(companyId).stream().filter(employee->employee.getState() != 0).toList();
	}
	
	public Employee findById(String s) {
		
		Optional<Employee> employee = employeeRepository.findById(s);
		if (employee.isEmpty()) throw new Java15Grup2ProjeAppException(ErrorType.NOT_FOUND_USER);
		else if (employee.get().getState() == 0) throw new Java15Grup2ProjeAppException(ErrorType.NOT_FOUND_USER);
		return employee.get();
	}
	
	public List<Employee> findAllByManagerId(String id) {
		return employeeRepository.findAllByManagerId(id).stream().filter(employee->employee.getState() != 0).toList();
	}
	
	public void save(Employee employee) {
		employeeRepository.save(employee);
	}
	
	public Employee findByEmail(String email) {
		Optional<Employee> optEmployee = employeeRepository.findByEmail(email);
		if (optEmployee.isEmpty()) throw new Java15Grup2ProjeAppException(ErrorType.NOT_FOUND_USER);
		else if (optEmployee.get().getState() == 0) throw new Java15Grup2ProjeAppException(ErrorType.NOT_FOUND_USER);
		return optEmployee.get();
	}
	
	public void delete(Employee employee) {
		employee.setState(0);
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
	
	public List<Employee> findAllByCompanyId(String companyId) {
		return employeeRepository.findAllByCompanyIdAndState(companyId, 1);
	}
}