package com.pool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class StudentpoolConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentpoolConfigServerApplication.class, args);
	}

}
