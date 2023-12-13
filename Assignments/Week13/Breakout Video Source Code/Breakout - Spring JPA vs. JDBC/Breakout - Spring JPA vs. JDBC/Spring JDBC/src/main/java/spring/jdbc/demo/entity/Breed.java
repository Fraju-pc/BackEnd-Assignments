// Copyright (c) 2023 by Promineo Tech.

package spring.jdbc.demo.entity;

import java.util.HashSet;
import java.util.Set;
import lombok.Data;

/**
 * This class maps data in the breed table. The Lombok @Data annotation is used
 * to add getters and setters, equals(), hashCode() and toString().
 * 
 * @author Promineo
 *
 */
@Data
public class Breed {
  private Long breedId;
  private String breedName;
  private String description;
  private Set<AltName> altNames = new HashSet<>();
  private Set<Category> categories = new HashSet<>();
}
