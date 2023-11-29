/**
 * 
 */
package com.promineotech.person.service;

import java.util.NoSuchElementException;
import com.promineotech.person.dao.PersonDao;
import com.promineotech.person.model.Person;

/**
 * This class is the service that manages interactions with the person table. Normally, the service wouldn't have
 * methods for creating and dropping tables but this is convenient for the BDD demo. The database tables are normally
 * created externally without involving the application code.
 * 
 * @author Promineo
 *
 */
public class PersonService {
  private PersonDao personDao = new PersonDao();


  /**
   * This simply calls the DAO class to create the person table. It is called by the functional test.
   */
  public void createPersonTable() {
    personDao.createPersonTable();
  }

  /**
   * This calls the DAO class to drop the person table. It is called by the functional test.
   */
  public void dropPersonTable() {
    personDao.dropPersonTable();
  }

  /**
   * This calls the DAO class to add a {@link Person} record to the person table.
   * 
   * @param name
   */
  public void addPerson(String name) {
    personDao.addPerson(name);
  }

  /**
   * This retrieves a {@link Person} object given the person name. The business rules specify that the name be
   * capitalized when it is returned so this happens here.
   * 
   * @param name The name to search for
   * @return The capitalized person name
   * @throws NoSuchElementException Thrown if the Person record with the given name is not found.
   */
  public String findName(String name) {
    Person person =
        personDao.findName(name).orElseThrow(() -> new NoSuchElementException("The name " + name + " is not found"));

    String dbname = person.getPersonName();
    return dbname.substring(0, 1).toUpperCase() + dbname.substring(1);
  }

}
