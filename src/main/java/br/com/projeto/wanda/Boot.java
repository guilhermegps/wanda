package br.com.projeto.wanda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class Boot
{
   public static void main(String[] args)
   {
      SpringApplication.run(Boot.class, args);
   }
}
