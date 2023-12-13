// Copyright (c) 2023 by Promineo Tech.

package spring.jpa.demo.entity;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;

/**
 * This class provides JPA with information on how to manage the breed table.
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
public class Breed {
  /*
   * @Id tells JPA that this is an identity (primary key) field. @GeneratedValue
   * tells JPA that the datasource will manage the primary key.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long breedId;

  private String breedName;

  @Column(length = 2048)
  private String description;

  /*
   * This is the "owning" side of the one-to-many relationship between breed and
   * alt_names.
   */
  @OneToMany(mappedBy = "breed", cascade = CascadeType.ALL)
  private Set<AltName> altNames = new HashSet<>();

  /*
   * This is the "owning" side of the many-to-many relationship between breed
   * and category. This specifies the characteristics of the join table that
   * links the two entities.
   */
  // @formatter:off
  @ManyToMany(cascade = CascadeType.PERSIST)
  @JoinTable(name = "breed_category",
      joinColumns = @JoinColumn(name = "breed_id"),
      inverseJoinColumns = @JoinColumn(name = "category_id")
  )
  private Set<Category> categories = new HashSet<>();
  // @formatter:on
}
