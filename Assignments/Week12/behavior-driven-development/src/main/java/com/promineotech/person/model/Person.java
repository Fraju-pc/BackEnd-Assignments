/**
 * 
 */
package com.promineotech.person.model;

/**
 * This class represents a (really **really** simple) abstraction of a person. It only contains a row ID (primary key
 * value) and a name. Person objects are immutable - once created they cannot be modified.
 * 
 * @author Promineo
 *
 */
public class Person {
  private int personPK;
  private String personName;

  /**
   * Create a new Person object with the give ID (primary key valud) and name.
   * 
   * @param personPK The person ID
   * @param personName The person name
   */
  public Person(int personPK, String personName) {
    this.personPK = personPK;
    this.personName = personName;
  }

  /**
   * @return the personPK
   */
  public int getPersonPK() {
    return personPK;
  }

  /**
   * @return the personName
   */
  public String getPersonName() {
    return personName;
  }

}
