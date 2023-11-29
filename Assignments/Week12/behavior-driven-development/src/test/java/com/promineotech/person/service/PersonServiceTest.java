/**
 * 
 */
package com.promineotech.person.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.promineotech.person.model.Person;

/**
 * This test class verifies writing and reading of {@link Person} data to an in-memory database. The database is H2,
 * which can mimic a MySQL database among others. If you're just writing tests, using an in-memory database is often
 * more convenient than using an external database. It is always online and is so fast that the entire database can be
 * dropped and created prior to each test.
 * 
 * @author Promineo
 *
 */
class PersonServiceTest {
  private PersonService personService;

  /**
   * JUnit calls this method before each test is executed. This method creates a new PersonService object and creates
   * the table used to store Person records.
   */
  @BeforeEach
  void setUp() {
    personService = new PersonService();
    personService.createPersonTable();
  }

  /**
   * JUnit calls this method after each test has completed. This method drops the person table from the database.
   */
  @AfterEach
  void tearDown() {
    personService.dropPersonTable();
  }

  /**
   * This method tests that Person records can be added and retrieved correctly and that the retrieved Person name is
   * correctly capitalized.
   */
  @Test
  void assertThatNamesAreCorrectlyAddedToAndRetrievedFromTheDatabase() {
    // Given: a list of names
    List<String> names = List.of("sarah", "bill", "amit", "kavitha");

    // When: the names are added to the database
    names.forEach(name -> personService.addPerson(name));

    // Then: the names are capitalized correctly when they are retrieved
    assertThat(personService.findName("sarah")).isEqualTo("Sarah");
    assertThat(personService.findName("bill")).isEqualTo("Bill");
    assertThat(personService.findName("amit")).isEqualTo("Amit");
    assertThat(personService.findName("kavitha")).isEqualTo("Kavitha");

    // And: that the service throws an exception for a Person record that does not exist
    assertThatThrownBy(() -> personService.findName("maria")).isInstanceOf(NoSuchElementException.class);
  }

}
