// Copyright (c) 2023 by Promineo Tech.

package spring.jdbc.demo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import spring.jdbc.demo.entity.Breed;
import spring.jdbc.demo.service.BreedService;

/**
 * This class starts Spring Boot. When Boot starts up, it loads schema.sql and
 * data.sql from src/main/resources. See application.yaml (src/main/resources)
 * for details.
 * 
 * @author Promineo
 *
 */
@SpringBootApplication
public class SpringJDBCDemo implements CommandLineRunner {
  @Autowired
  private BreedService breedService;

  /**
   * Starts Spring Boot.
   * 
   * @param args
   */
  public static void main(String[] args) {
    SpringApplication.run(SpringJDBCDemo.class, args);
  }

  /**
   * This is required by the {@link CommandLineRunner} interface. It allows us
   * to run some code after Boot has initialized.
   * 
   * This method retrieves all the bunny breeds and prints them at the console.
   */
  @Override
  public void run(String... args) throws Exception {
    List<Breed> breeds = breedService.retrieveAllBreeds();
    breeds.forEach(System.out::println);
  }
}
