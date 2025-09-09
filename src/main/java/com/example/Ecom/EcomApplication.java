package com.example.Ecom;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class EcomApplication {
	public static void main(String[] args) {

		Dotenv dotenv = Dotenv.configure().directory("./").ignoreIfMalformed().ignoreIfMissing().load();

		if(dotenv.get("DB_URL") != null){
			System.setProperty("DB_URL",dotenv.get("DB_URL"));
		}
		if(dotenv.get("DB_USER") != null){
			System.setProperty("DB_USER", dotenv.get("DB_USER"));
		}
		if(dotenv.get("DB_PASSWORD") != null){
			System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
		}

		SpringApplication.run(EcomApplication.class, args);
	}

	@Bean
	CommandLineRunner printActiveProfile(Environment env){
		return (args)->{
			System.out.println(">>> Active Profiles:"+ String.join(", ", env.getActiveProfiles()));
		};
	}

}
