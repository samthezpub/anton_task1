package com.example.rest;

import org.mapstruct.MapperConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableFeignClients
@EntityScan(basePackages = "com.example.db.Entity")
@SpringBootApplication(scanBasePackages = "com.example")
@EnableJpaRepositories(basePackages = "com.example.db.Repository")
@MapperConfig(implementationPackage = "com.example.buisness.Mapper")
public class AntonTask1Application {

  public static void main(String[] args) {
    SpringApplication.run(AntonTask1Application.class, args);
  }
}
