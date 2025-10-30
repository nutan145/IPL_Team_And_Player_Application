package com.nt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableEurekaServer
@Slf4j
public class BootMsProj13RDServerEurekaServerApp {

	public static void main(String[] args) {
		log.debug("Start of  main(-)  in Eureka server App");
		SpringApplication.run(BootMsProj13RDServerEurekaServerApp.class, args);
		log.debug("End of  main(-)  in Eureka server App");
		
	}

}
