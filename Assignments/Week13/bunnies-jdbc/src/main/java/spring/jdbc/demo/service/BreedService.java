// Copyright (c) 2023 by Promineo Tech.

package spring.jdbc.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.jdbc.demo.dao.BreedDao;
import spring.jdbc.demo.entity.Breed;

/**
 * This service-layer class is called by the main class to fetch all rows and
 * print them.
 * 
 * @author Promineo
 *
 */
@Service
public class BreedService {
  @Autowired
  private BreedDao breedDao;

  /**
   * Calls the DAO to retrieve all bunny breeds. This is run in a transaction.
   * 
   * @return The list of breeds.
   */
  @Transactional
  public List<Breed> retrieveAllBreeds() {
    return breedDao.findAll();
  }

}
