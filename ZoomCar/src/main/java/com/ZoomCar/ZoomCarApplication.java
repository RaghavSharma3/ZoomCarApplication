package com.ZoomCar;

import com.ZoomCar.entity.Role;
import com.ZoomCar.entity.User;
import com.ZoomCar.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@RequiredArgsConstructor
public class ZoomCarApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(ZoomCarApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if(userRepository.findByEmail("harsh@gmail.com").isEmpty()) {
			User user = User.builder()
					.username("Harsh Khurana")
					.email("harsh@gmail.com")
					.password(passwordEncoder.encode("1234"))
					.isBlocked("No")
					.role(Role.ROLE_ADMIN)
					.build();
			userRepository.save(user);
		}
	}

}
