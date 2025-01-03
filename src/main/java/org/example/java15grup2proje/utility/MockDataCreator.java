package org.example.java15grup2proje.utility;

import lombok.RequiredArgsConstructor;
import org.example.java15grup2proje.controller.AdminController;
import org.example.java15grup2proje.entity.*;
import org.example.java15grup2proje.entity.enums.*;
import org.example.java15grup2proje.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
@RequiredArgsConstructor
public class MockDataCreator {
	private final CompanyRepository companyRepository;
	private final ManagerRepository managerRepository;
	private final EmployeeRepository employeeRepository;
	private final AdminRepository adminRepository;
	private final LeaveRepository leaveRepository;
	private final CommentRepository commentRepository;
	@Bean
	CommandLineRunner createMockData() {
		return args -> {
			if (adminRepository.count() == 0){
				Admin admin = Admin.builder()
						.email("admin@mail.com")
						.password(PasswordHasher.passwordHash("Qweqwe123!"))
						.role(ERole.ADMIN)
						.isSuperAdmin(true)
				                   .build();
				adminRepository.save(admin);
			}
			// Mock Company Data
			Manager manager = null;
			// Mock Leave Data
			if(leaveRepository.count() == 0){
			
			}
			// Mock Employee Data
			if (employeeRepository.count() == 0) {
				
				Company company = Company.builder()
				                         .companyName("Ergun Holding")
				                         .companyAddress("Kagithane/ Istanbul")
				                         .companyMail("company@ergunler.com")
				                         .industry("Informatics")
				                         .employeeCount(4L)
				                         .companyLogoUrl("https://mir-s3-cdn-cf.behance.net/project_modules/1400/224d1c165480227.6408444fca796.jpg")
				                         .isPaidMember(true)
						.companyWebSite("https://www.ergunholding.com/")
						.establishedDate(1483436272000L)
				                         .build();
				company = companyRepository.save(company);
				System.out.println("Mock Company Created: " + company);
				
				manager = Manager.builder().name("John").surname("Doe").email("john.doe@example.com")
				                 .password(PasswordHasher.passwordHash("Alperen1+")).gender(EGender.MAN)
				                 .companyId(company.getId()).role(ERole.MANAGER)
				                 .birthDate(TimeConverter.localDateToEpoch(LocalDate.of(2000, 10, 10)))
				                 .department(EDepartment.IT).title("Backend Developer").phoneNumber("05554443322")
				                 .address("Istanbul/Turkey")
				                 .pictureUrl("https://images2.fanpop.com/images/photos/7900000/JOHN-DOE-john-doe-7969094-2087-2560.jpg")
				                 .build();
				manager = managerRepository.save(manager);
				System.out.println("Mock Manager Created: " + manager);
				
				Comment comment = Comment.builder()
				                         .comment("We have been a leader in our industry for over 20 years, offering " + "top-quality products and services. Innovation, sustainability, and customer satisfaction are central to everything that is done. The highest standards are consistently upheld, ensuring that the needs of clients are met with professionalism and care. Long-term relationships with partners and customers are nurtured, built on trust and reliability. A strong commitment to excellence is maintained across all aspects of the business. The journey of growth and success continues, with a focus on making a positive impact in the industry.")
				                         .managerId(manager.getId()).companyId(manager.getCompanyId()).build();
				commentRepository.save(comment);
				
				Employee employee2 = Employee.builder()
				                            .name("Jane")
				                            .surname("Smith")
						.wage(50000L)
				                            .email("jane.smith@example.com")
				                            .password(PasswordHasher.passwordHash("Alperen1+"))
				                            .gender(EGender.WOMAN)
						.phoneNumber("09998887766")
						.companyId("Example Company ID")
				                            .managerId((manager == null)?"0":manager.getId())
				                            .role(ERole.EMPLOYEE)
						.pictureUrl("https://townsquare.media/site/180/files/2018/02/Jane-Smith-Edited.jpg?w=1200&h=0&zc=1&s=0&a=t&q=89")
				                            .build();
				employeeRepository.save(employee2);
				System.out.println("Mock Employee Created: " + employee2);
			

				 Employee employee = Employee.builder()
						.name("Burak")
						.surname("BB")
						.email("burak1@example.com")
						.password(PasswordHasher.passwordHash("Qweqwe123!"))
						.gender(EGender.MAN)
						.managerId((manager == null)?"0":manager.getId())
						.role(ERole.EMPLOYEE)
						.build();
				employeeRepository.save(employee);
				System.out.println("Mock Employee Created: " + employee);
			
				 employee = Employee.builder()
						.name("Mary")
						.surname("Christmas")
						.email("burak2@example.com")
						.password(PasswordHasher.passwordHash("Qweqwe123!"))
						.gender(EGender.WOMAN)
						.managerId((manager == null)?"0":manager.getId())
						.role(ERole.EMPLOYEE)
						.build();
				employeeRepository.save(employee);
				System.out.println("Mock Employee Created: " + employee);
			
				 employee = Employee.builder()
						.name("Emirhan")
						.surname("EE")
						.email("burak3@example.com")
						.password(PasswordHasher.passwordHash("Qweqwe123!"))
						.gender(EGender.MAN)
						.managerId((manager == null)?"0":manager.getId())
						.role(ERole.EMPLOYEE)
						.build();
				employeeRepository.save(employee);
				System.out.println("Mock Employee Created: " + employee);
			
				 employee = Employee.builder()
						.name("Hilal")
						.surname("Yıldız")
						.email("burak4@example.com")
						.password(PasswordHasher.passwordHash("Qweqwe123!"))
						.gender(EGender.WOMAN)
						.managerId((manager == null)?"0":manager.getId())
						.role(ERole.EMPLOYEE)
						.build();
				employeeRepository.save(employee);
				System.out.println("Mock Employee Created: " + employee);
			
				 employee = Employee.builder()
						.name("Quenn")
						.surname("King")
						.email("burak5@example.com")
						.password(PasswordHasher.passwordHash("Qweqwe123!"))
						.gender(EGender.WOMAN)
						.managerId((manager == null)?"0":manager.getId())
						.role(ERole.EMPLOYEE)
						.build();
				employeeRepository.save(employee);
				System.out.println("Mock Employee Created: " + employee);
			
				 employee = Employee.builder()
						.name("Ajda")
						.surname("Pekkan")
						.email("burak6@example.com")
						.password(PasswordHasher.passwordHash("Qweqwe123!"))
						.gender(EGender.WOMAN)
						.managerId((manager == null)?"0":manager.getId())
						.role(ERole.EMPLOYEE)
						.build();
				employeeRepository.save(employee);
				System.out.println("Mock Employee Created: " + employee);
			
				 employee = Employee.builder()
						.name("Mauro")
						.surname("İcardi")
						.email("burak7@example.com")
						.password(PasswordHasher.passwordHash("Qweqwe123!"))
						.gender(EGender.MAN)
						.managerId((manager == null)?"0":manager.getId())
						.role(ERole.EMPLOYEE)
						.build();
				employeeRepository.save(employee);
				System.out.println("Mock Employee Created: " + employee);
			
				 employee = Employee.builder()
						.name("Alperen")
						.surname("BV")
						.email("burak8@example.com")
						.password(PasswordHasher.passwordHash("Qweqwe123!"))
						.gender(EGender.MAN)
						.managerId((manager == null)?"0":manager.getId())
						.role(ERole.EMPLOYEE)
						.build();
				employeeRepository.save(employee);
				System.out.println("Mock Employee Created: " + employee);
			
				 employee = Employee.builder()
						.name("Mehmet")
						.surname("TN")
						.email("burak9@example.com")
						.password(PasswordHasher.passwordHash("Qweqwe123!"))
						.gender(EGender.MAN)
						.managerId((manager == null)?"0":manager.getId())
						.role(ERole.EMPLOYEE)
						.build();
				employeeRepository.save(employee);
				System.out.println("Mock Employee Created: " + employee);
			
				 employee = Employee.builder()
						.name("Fernado")
						.surname("Muslera")
						.email("burak10@example.com")
						.password(PasswordHasher.passwordHash("Qweqwe123!"))
						.gender(EGender.MAN)
						.managerId((manager == null)?"0":manager.getId())
						.role(ERole.EMPLOYEE)
						.build();
				employeeRepository.save(employee);
				System.out.println("Mock Employee Created: " + employee);
			
				 employee = Employee.builder()
						.name("Simge")
						.surname("Sagin")
						.email("burak11@example.com")
						.password(PasswordHasher.passwordHash("Qweqwe123!"))
						.gender(EGender.MAN)
						.managerId((manager == null)?"0":manager.getId())
						.role(ERole.EMPLOYEE)
						.build();
				employeeRepository.save(employee);
				System.out.println("Mock Employee Created: " + employee);
			

				Manager manager2 = Manager.builder()
						.name("Baris")
						.surname("Demirci")
						.email("Baris.Demirci@example.com")
						.password(PasswordHasher.passwordHash("Qweqwe123!"))
						.gender(EGender.MAN)
						.companyId("Example Company ID")
						.role(ERole.MANAGER)
						.phoneNumber("05554443322")
						.address("Istanbul/Turkey")
						.pictureUrl("https://images2.fanpop.com/images/photos/7900000/JOHN-DOE-john-doe-7969094-2087-2560.jpg")
						.build();
				managerRepository.save(manager2);
				System.out.println("Mock Manager Created: " + manager2);
				
				Expense expense = Expense.builder()
						.expenseDate(System.currentTimeMillis())
						.expenseState(EState.PENDING)
						.managerId(manager.getId())
						.personnelId(employee2.getId())
						.title("sandalye")
						.description("basit ahşap bir sandalye satın alındı")
				                         .build();
				
				for (int i = 0; i < 20; i++) {
					Leave leave = Leave.builder()
					                   .managerId(manager.getId())
					                   .description("fdslkf" + i + "fdsa" + Math.pow(i, 20))
					                   .leaveState(EState.values()[i % 3])
					                   .leaveType(ELeaveType.values()[i % ELeaveType.values().length])
					                   .endDate(1730935553000L + i*1000000000)
					                   .startDate(1730025553000L + i*1000000000 )
					                   .build();
					leaveRepository.save(leave);
				}
			}
			
		};
	}
}