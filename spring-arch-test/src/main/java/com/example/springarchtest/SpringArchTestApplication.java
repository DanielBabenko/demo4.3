package com.example.springarchtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import com.example.springarchtest.model.User;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.springarchtest.repository.UserRepository;

//@Configuration
//@EnableAutoConfiguration
//@ComponentScan
@SpringBootApplication
public class SpringArchTestApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringArchTestApplication.class, args);
	}

	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String...args) throws Exception {
		this.userRepository.save(new User("Ramesh", "Fadatare", "ramesh@gmail.com"));
		this.userRepository.save(new User("Tom", "Cruise", "tom@gmail.com"));
		this.userRepository.save(new User("Tony", "Stark", "tony@gmail.com"));
	}
}
