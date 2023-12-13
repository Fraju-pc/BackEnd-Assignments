// Copyright (c) 2023 by Promineo Tech.

package spring.jpa.demo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import spring.jpa.demo.entity.Breed;
import spring.jpa.demo.service.BreedService;

/**
 * This class starts Spring Boot. When Boot starts up, Hibernate creates the
 * data tables. Then, Spring Boot loads data.sql from src/main/resources. See
 * application.yaml (src/main/resources) for details.
 * 
 * @author Promineo
 *
 */
@SpringBootApplication
public class SpringJPADemo implements CommandLineRunner {
  @Autowired
  private BreedService breedService;

  /**
   * Starts Spring Boot.
   * 
   * @param args
   */
  public static void main(String[] args) {
    SpringApplication.run(SpringJPADemo.class, args);
  }

  /**
   * This is required by the {@link CommandLineRunner} interface. It allows us
   * to run some code after Boot has initialized.
   * 
   * This method retrieves all the bunny breeds and prints them at the console
   * using Spring JPA.
   */
  @Override
  public void run(String... args) throws Exception {
    List<Breed> breeds = breedService.retrieveAllBreeds();
    breeds.forEach(System.out::println);
  }
}
