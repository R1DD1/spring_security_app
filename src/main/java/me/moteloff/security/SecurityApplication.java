package me.moteloff.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}


//    @Bean
//    CommandLineRunner run(UserRepository userRepository, PasswordEncoder passwordEncode){
//        return args ->{
//            User admin = new User(1, "admin", "email@gmail.com", UUID.randomUUID(), passwordEncode.encode("password"));
//
//            userRepository.save(admin);
//        };
//    }

}
