package br.com.projeto.wanda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.com.projeto.wanda.repository.base.BaseRepositoryImpl;

/**
 * @author <a href="https://github.com/guilhermegps"> Guilherme GPS </a>
 * 
 */
@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)
@SpringBootApplication
public class WandaApplication {
   public static void main(String[] args) {
      SpringApplication.run(WandaApplication.class, args);
   }
}
