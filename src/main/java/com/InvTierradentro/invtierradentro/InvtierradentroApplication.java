package com.InvTierradentro.invtierradentro;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
public class InvtierradentroApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(InvtierradentroApplication.class, args);
	}
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {
		String password="12345";
		String passwordencoder=passwordEncoder.encode(password);
		System.out.println(passwordencoder);
	}

}
