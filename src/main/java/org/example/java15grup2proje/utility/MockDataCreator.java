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
			Manager manager = null;
			if (managerRepository.count() == 0) {
				manager = Manager.builder()
				                         .name("John")
				                         .surname("Doe")
				                         .email("john.doe@example.com")
				                         .password(PasswordHasher.passwordHash("Alperen1+"))
				                         .gender(EGender.MAN)
				                         .companyId("Example Company ID")
				                         .role(ERole.MANAGER)
						.department(EDepartment.IT)
						.title("Backend Developer")
						.phoneNumber("05554443322")
						.address("Istanbul/Turkey")
						.pictureUrl("https://images2.fanpop.com/images/photos/7900000/JOHN-DOE-john-doe-7969094-2087-2560.jpg")
				                         .build();
				manager = managerRepository.save(manager);
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
						.companyId("Example Company ID")
				                            .managerId((manager == null)?"0":manager.getId())
				                            .role(ERole.EMPLOYEE)
				                            .build();
				employeeRepository.save(employee);
				System.out.println("Mock Employee Created: " + employee);
			}

			if (employeeRepository.count() == 0) {
				Employee employee = Employee.builder()
						.name("Burak")
						.surname("BB")
						.email("burak.bb@example.com")
						.password(PasswordHasher.passwordHash("B12345"))
						.gender(EGender.MAN)
						.managerId((manager == null)?"0":manager.getId())
						.role(ERole.EMPLOYEE)
						.build();
				employeeRepository.save(employee);
				System.out.println("Mock Employee Created: " + employee);
			}
			if (employeeRepository.count() == 0) {
				Employee employee = Employee.builder()
						.name("Mary")
						.surname("Christmas")
						.email("mary.Christmas@example.com")
						.password(PasswordHasher.passwordHash("B12345"))
						.gender(EGender.WOMAN)
						.managerId((manager == null)?"0":manager.getId())
						.role(ERole.EMPLOYEE)
						.build();
				employeeRepository.save(employee);
				System.out.println("Mock Employee Created: " + employee);
			}
			if (employeeRepository.count() == 0) {
				Employee employee = Employee.builder()
						.name("Emirhan")
						.surname("EE")
						.email("Emirhan.EE@example.com")
						.password(PasswordHasher.passwordHash("B12345"))
						.gender(EGender.MAN)
						.managerId((manager == null)?"0":manager.getId())
						.role(ERole.EMPLOYEE)
						.build();
				employeeRepository.save(employee);
				System.out.println("Mock Employee Created: " + employee);
			}
			if (employeeRepository.count() == 0) {
				Employee employee = Employee.builder()
						.name("Hilal")
						.surname("Yıldız")
						.email("hilal.yildizbayraklaribayrakyapan@example.com")
						.password(PasswordHasher.passwordHash("B12345"))
						.gender(EGender.WOMAN)
						.managerId((manager == null)?"0":manager.getId())
						.role(ERole.EMPLOYEE)
						.build();
				employeeRepository.save(employee);
				System.out.println("Mock Employee Created: " + employee);
			}
			if (employeeRepository.count() == 0) {
				Employee employee = Employee.builder()
						.name("Quenn")
						.surname("King")
						.email("Quen.King@example.com")
						.password(PasswordHasher.passwordHash("B12345"))
						.gender(EGender.WOMAN)
						.managerId((manager == null)?"0":manager.getId())
						.role(ERole.EMPLOYEE)
						.build();
				employeeRepository.save(employee);
				System.out.println("Mock Employee Created: " + employee);
			}
			if (employeeRepository.count() == 0) {
				Employee employee = Employee.builder()
						.name("Ajda")
						.surname("Pekkan")
						.email("Ajda.Pekkan@example.com")
						.password(PasswordHasher.passwordHash("B12345"))
						.gender(EGender.WOMAN)
						.managerId((manager == null)?"0":manager.getId())
						.role(ERole.EMPLOYEE)
						.build();
				employeeRepository.save(employee);
				System.out.println("Mock Employee Created: " + employee);
			}
			if (employeeRepository.count() == 0) {
				Employee employee = Employee.builder()
						.name("Mauro")
						.surname("İcardi")
						.email("Mauro.Icardi@example.com")
						.password(PasswordHasher.passwordHash("B12345"))
						.gender(EGender.MAN)
						.managerId((manager == null)?"0":manager.getId())
						.role(ERole.EMPLOYEE)
						.build();
				employeeRepository.save(employee);
				System.out.println("Mock Employee Created: " + employee);
			}
			if (employeeRepository.count() == 0) {
				Employee employee = Employee.builder()
						.name("Alperen")
						.surname("BV")
						.email("Alperen.Bv@example.com")
						.password(PasswordHasher.passwordHash("B12345"))
						.gender(EGender.MAN)
						.managerId((manager == null)?"0":manager.getId())
						.role(ERole.EMPLOYEE)
						.build();
				employeeRepository.save(employee);
				System.out.println("Mock Employee Created: " + employee);
			}
			if (employeeRepository.count() == 0) {
				Employee employee = Employee.builder()
						.name("Mehmet")
						.surname("TN")
						.email("Mehmet.Tn@example.com")
						.password(PasswordHasher.passwordHash("B12345"))
						.gender(EGender.MAN)
						.managerId((manager == null)?"0":manager.getId())
						.role(ERole.EMPLOYEE)
						.build();
				employeeRepository.save(employee);
				System.out.println("Mock Employee Created: " + employee);
			}
			if (employeeRepository.count() == 0) {
				Employee employee = Employee.builder()
						.name("Fernado")
						.surname("Muslera")
						.email("Fernando.Muslera@example.com")
						.password(PasswordHasher.passwordHash("B12345"))
						.gender(EGender.MAN)
						.managerId((manager == null)?"0":manager.getId())
						.role(ERole.EMPLOYEE)
						.build();
				employeeRepository.save(employee);
				System.out.println("Mock Employee Created: " + employee);
			}
			if (employeeRepository.count() == 0) {
				Employee employee = Employee.builder()
						.name("Simge")
						.surname("Sagin")
						.email("Simge.Sagin@example.com")
						.password(PasswordHasher.passwordHash("B12345"))
						.gender(EGender.MAN)
						.managerId((manager == null)?"0":manager.getId())
						.role(ERole.EMPLOYEE)
						.build();
				employeeRepository.save(employee);
				System.out.println("Mock Employee Created: " + employee);
			}

			if (managerRepository.count() == 0) {
				manager = Manager.builder()
						.name("Baris")
						.surname("Demirci")
						.email("Baris.Demirci@example.com")
						.password(PasswordHasher.passwordHash("B12345"))
						.gender(EGender.MAN)
						.companyId("Example Company ID")
						.role(ERole.MANAGER)
						.phoneNumber("05554443322")
						.address("Istanbul/Turkey")
						.pictureUrl("https://images2.fanpop.com/images/photos/7900000/JOHN-DOE-john-doe-7969094-2087-2560.jpg")
						.build();
				managerRepository.save(manager);
				System.out.println("Mock Manager Created: " + manager);
			}
		};
	}
}