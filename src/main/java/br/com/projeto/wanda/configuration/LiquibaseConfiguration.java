package br.com.projeto.wanda.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import liquibase.integration.spring.SpringLiquibase;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class LiquibaseConfiguration {
	@Autowired
	DataSource dataSource;
	
	@Bean
	public SpringLiquibase liquibase() {
	    SpringLiquibase liquibase = new SpringLiquibase();
	    try {
		    liquibase.setChangeLog("classpath:br/com/wanda/db/changelog/db.changelog-master.xml");
		    liquibase.setDataSource(dataSource);
	    } catch(Exception e) {
	    	log.error(e.getMessage(), e);
	    }
	    return liquibase;
	}
}
