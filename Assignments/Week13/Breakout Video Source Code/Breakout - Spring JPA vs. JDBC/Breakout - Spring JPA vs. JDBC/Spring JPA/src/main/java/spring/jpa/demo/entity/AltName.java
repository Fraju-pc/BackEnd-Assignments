// Copyright (c) 2023 by Promineo Tech.

package spring.jpa.demo.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * This class provides JPA with information on how to manage the alt_name table.
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
public class AltName {
  /*
   * @Id tells JPA that this is an identity (primary key) field. @GeneratedValue
   * tells JPA that the datasource will manage the primary key.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long alternateId;

  private String alternateName;

  /*
   * This is the "owned" side of a one-to-many relationship between alt_name and
   * breed. This variable contains a recursive reference to the Breed class.
   * Because of this it must be excluded from the .equals(), .hashCode(), and
   * .toString() methods.
   */
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "breed_id", nullable = true)
  private Breed breed;
}
