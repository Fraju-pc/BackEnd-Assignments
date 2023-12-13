// Copyright (c) 2023 by Promineo Tech.

package spring.jpa.demo.entity;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * This class provides JPA with information on how to manage the category table.
 * 
 * @Entity Marks the class as one JPA will manage.
 * 
 * @Data Creates getters and setters for the instance variables. Also creates
 *       the .equals() and .hashCode() methods, as well as a .toString() method.
 * 
 * @author Promineo
 *
 */
@Entity
@Data
public class Category {
  /*
   * @Id tells JPA that this is an identity (primary key) field. @GeneratedValue
   * tells JPA that the datasource will manage the primary key.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long categoryId;

  private String categoryName;

  /*
   * This is the "owned" side of a many-to-many relationship between category
   * and breed. This variable contains a recursive reference to the Breed class.
   * Because of this it must be excluded from the .equals(), .hashCode(), and
   * .toString() methods.
   */
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  @ManyToMany(mappedBy = "categories")
  private Set<Breed> breeds = new HashSet<>();
}
