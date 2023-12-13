// Copyright (c) 2023 by Promineo Tech.

package spring.jpa.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.jpa.demo.dao.BreedDao;
import spring.jpa.demo.entity.Breed;

/**
 * This class manages transactions and interactions with the data layer.
 * 
 * @author Promineo
 *
 */
@Service
public class BreedService {
  @Autowired
  private BreedDao breedDao;

  /**
   * This method calls the breed DAO to retrieve all the breed rows. Alternate
   * names and categories are also returned. 
   */
  @Transactional
  public List<Breed> retrieveAllBreeds() {
    List<Breed> breeds = breedDao.findAll();

    /*
     * Just the act of getting the size of each AltName and Category Set causes
     * JPA to load them from the appropriate data tables. If they are not
     * completely loaded here, JPA will attempt to load them when they are
     * printed to the console in the main method. That will load them outside
     * the transaction and a lazy init error will be generated.
     * 
     * The @ManyToMany and @ManyToOne annotations have a fetch attribute that
     * can be used to specify a loading strategy (i.e., lazy or eager). But the
     * loading strategy should not be in the entity class because the entity is
     * not in a position to know what strategy should be applied in every
     * instance. Instead, this is best done in a service method because the
     * service is in a position of oversight so it knows when and how to apply
     * the loading strategy.
     */
    for(Breed breed : breeds) {
      breed.getAltNames().size();
      breed.getCategories().size();
    }

    return breeds;
  }
}
