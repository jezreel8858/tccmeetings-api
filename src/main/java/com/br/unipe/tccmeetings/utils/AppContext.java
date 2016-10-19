package com.br.unipe.tccmeetings.utils;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EnableTransactionManagement
@ComponentScan( basePackages = "org.wpattern.mutrack.simple")
@EnableJpaRepositories(basePackages = "org.wpattern.mutrack.simple")
@EntityScan(basePackages = "org.wpattern.mutrack.simple")
public class AppContext extends SpringBootServletInitializer{

	@Bean(name = "applicationProperty")
	public ApplicationProperty getApplicationProperty() {
		return new ApplicationProperty();
	}

	@Bean(name = "passwordEncoder")
	public StandardPasswordEncoder getStandardPasswordEncoder() {
		return new StandardPasswordEncoder(getApplicationProperty().getSecret());
	}

}
