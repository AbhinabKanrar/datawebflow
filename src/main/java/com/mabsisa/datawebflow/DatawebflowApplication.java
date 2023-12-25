package com.mabsisa.datawebflow;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.annotation.EnableRetry;


@SpringBootApplication
@EnableRetry
public class DatawebflowApplication {

  public static void main(String[] args) {
    SpringApplication.run(DatawebflowApplication.class, args);
  }

  @Bean
  CommandLineRunner run() {
    return args -> System.out.println("|||   Application Started   |||");
  }

}
