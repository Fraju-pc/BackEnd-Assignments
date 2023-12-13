// Copyright (c) 2023 by Promineo Tech.

package spring.jdbc.demo.entity;

import lombok.Data;

/**
 * This class maps data in the alt_name table. The Lombok @Data annotation is
 * used to add getters and setters, equals(), hashCode() and toString().
 * 
 * @author Promineo
 *
 */
@Data
public class AltName {
  private Long alternateId;
  private Long breedId;
  private String alternateName;
}
