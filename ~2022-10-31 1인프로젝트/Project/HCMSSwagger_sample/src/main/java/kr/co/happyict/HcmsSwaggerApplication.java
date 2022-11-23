package kr.co.happyict;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class HcmsSwaggerApplication {

  public static void main(String[] args) {
    SpringApplication.run(HcmsSwaggerApplication.class, args);
  }

}
