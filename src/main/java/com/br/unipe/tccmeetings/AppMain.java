package com.br.unipe.tccmeetings;

import com.br.unipe.tccmeetings.utils.AppContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppMain {

	public static void main(String[] args) {
		SpringApplication.run(AppContext.class, args);
	}

}
