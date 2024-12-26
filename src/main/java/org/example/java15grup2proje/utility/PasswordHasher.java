package org.example.java15grup2proje.utility;

import lombok.experimental.UtilityClass;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordHasher {
	static final int saltLength = 16;      // 16 byte salt (128 bit)
	static final int hashLength = 16;      // 32 byte hash (256 bit)
	static final int parallelism = 1;      // 1 thread
	static final int memory = 65536;       // 64 MB memory
	static final int iterations = 3;       // 3 iterations
	static final Argon2PasswordEncoder passwordEncoder = new Argon2PasswordEncoder(saltLength, hashLength, parallelism, memory
			, iterations);
	
	public static String passwordHash(String password) {
		return passwordEncoder.encode(password);
	}
	
	public static boolean compareHashedPassword(String password, String hashedPassword){
		boolean matches = passwordEncoder.matches(password,hashedPassword);
		if(matches){
			return true;
		}
		return false;
	}
}