// Copyright (c) 2023 by Promineo Tech.

package spring.jpa.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.jpa.demo.entity.Breed;

/**
 * This interface merely extends {@link JpaRepository}. Spring JPA provides the
 * backing implementation for the methods declared in the parent interfaces.
 * 
 * @author Promineo
 *
 */
public interface BreedDao extends JpaRepository<Breed, Long> {
}
