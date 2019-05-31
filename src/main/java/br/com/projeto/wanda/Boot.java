package br.com.projeto.wanda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication(scanBasePackages = "br.com.projeto.wanda")
//@EnableJpaRepositories(basePackages = "br.com.projeto.wanda.*.repositorios", 
//			repositoryBaseClass = BaseRepositoryImpl.class)
@EntityScan(basePackages = { "br.com.projeto.wanda.*.model", 
			"br.com.projeto.wanda.*.clientes.*.model", 
			"br.com.projeto.wanda.*.converters" })
@ComponentScan(basePackages = "br.com.projeto.wanda")
@EnableTransactionManagement
@EnableWebMvc
@EnableAsync
//@EnableScheduling
//@EnableAspectJAutoProxy
public class Boot
{
   public static void main(String[] args)
   {
      SpringApplication.run(Boot.class, args);
   }
}
