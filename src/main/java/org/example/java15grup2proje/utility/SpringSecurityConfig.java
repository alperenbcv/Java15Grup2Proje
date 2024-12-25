package org.example.java15grup2proje.utility;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.csrf(csrf -> csrf.disable()) // CSRF korumasını kapat
				.authorizeHttpRequests(auth -> auth
						.anyRequest().permitAll() // Tüm isteklere izin ver
				)
				.formLogin(form -> form.disable()) // Default login sayfasını kapat
				.httpBasic(httpBasic -> httpBasic.disable()); // Basic Auth'u kapat
		return http.build();
	}
}