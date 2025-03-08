package com.example.rest;

import org.mapstruct.MapperConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.example")
@EnableJpaRepositories(basePackages = "com.example.db.Repository")
@EntityScan(basePackages = "com.example.db.Entity")
@MapperConfig(implementationPackage = "com.example.buisness.Mapper")
public class AntonTask1Application {

  public static void main(String[] args) {
    SpringApplication.run(AntonTask1Application.class, args);
  }
}
