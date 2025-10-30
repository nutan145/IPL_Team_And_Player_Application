package com.nt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableAdminServer
@Slf4j
public class BootMsProj13AdminServerMiniProj1App {

	public static void main(String[] args) {
	  log.debug("start of main(-) in Admin server App");
		SpringApplication.run(BootMsProj13AdminServerMiniProj1App.class, args);
	  log.debug("end of main(-) in Admin server App");
	  
	}

}
