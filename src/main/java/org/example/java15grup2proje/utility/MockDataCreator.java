package org.example.java15grup2proje.utility;

import lombok.RequiredArgsConstructor;
import org.example.java15grup2proje.entity.Company;
import org.example.java15grup2proje.entity.Employee;
import org.example.java15grup2proje.entity.Manager;
import org.example.java15grup2proje.entity.enums.EDepartment;
import org.example.java15grup2proje.entity.enums.EGender;
import org.example.java15grup2proje.entity.enums.ERole;
import org.example.java15grup2proje.repository.CompanyRepository;
import org.example.java15grup2proje.repository.EmployeeRepository;
import org.example.java15grup2proje.repository.ManagerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;
import java.util.UUID;

@Configuration
@RequiredArgsConstructor
public class MockDataCreator {
	private final CompanyRepository companyRepository;
	private final ManagerRepository managerRepository;
	private final EmployeeRepository employeeRepository;
	
	@Bean
	CommandLineRunner createMockData() {
		return args -> {
			// Mock Company Data
			if (companyRepository.count() == 0) {
				Company company = Company.builder()
				                         .companyName("Example Company")
				                         .companyAddress("123 Main Street")
				                         .companyMail("contact@example.com")
				                         .industry("Technology")
				                         .employeeNumber(100L)
				                         .isPaidMember(true)
				                         .build();
				companyRepository.save(company);
				System.out.println("Mock Company Created: " + company);
			}
			
			// Mock Manager Data
			if (managerRepository.count() == 0) {
				Manager manager = Manager.builder()
				                         .name("John")
				                         .surname("Doe")
				                         .email("john.doe@example.com")
				                         .password(PasswordHasher.passwordHash("Alperen1+"))
				                         .gender(EGender.MAN)
				                         .companyId("Example Company ID")
				                         .role(ERole.MANAGER)
				                         .build();
				managerRepository.save(manager);
				System.out.println("Mock Manager Created: " + manager);
			}
			
			// Mock Employee Data
			if (employeeRepository.count() == 0) {
				Employee employee = Employee.builder()
				                            .name("Jane")
				                            .surname("Smith")
				                            .email("jane.smith@example.com")
				                            .password(PasswordHasher.passwordHash("Alperen1+"))
				                            .gender(EGender.WOMAN)
				                            .managerId(UUID.randomUUID().toString())
				                            .role(ERole.EMPLOYEE)
				                            .build();
				employeeRepository.save(employee);
				System.out.println("Mock Employee Created: " + employee);
			}
		};
	}
}