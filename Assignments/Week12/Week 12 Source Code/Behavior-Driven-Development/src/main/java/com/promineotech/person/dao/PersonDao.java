/**
 * 
 */
package com.promineotech.person.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import com.promineotech.person.model.Person;

/**
 * This class interacts with the H2 in-memory database in MySQL mode. When called by the functional test, the
 * connection, prepared statements, SQL, parameter injection and data are verified without writing any unit tests. Unit
 * tests wouldn't test any of that anyway. They have a hard time handling DAO classes without mocking all the behavior
 * out. If the tests do that they really aren't testing anything.
 * 
 * @author Promineo
 *
 */
public class PersonDao {
  private Connection conn;

  /**
   * This constructor establishes a connection with the H2 database.
   * 
   * @throws MemDbException Thrown if the connection cannot be established.
   */
  public PersonDao() {
    try {
      conn = DriverManager.getConnection("jdbc:h2:mem:test");
    } catch (SQLException e) {
      throw new MemDbException("Error obtaining connection", e);
    }
  }

  /**
   * Called by the functional test to create the person table.
   * 
   * @throws MemDbException Thrown if an error occurs creating or executing the prepared statement.
   */
  public void createPersonTable() {
    String sql = "" + "CREATE TABLE person (" + "person_pk INT NOT NULL PRIMARY KEY AUTO_INCREMENT, "
        + "person_name VARCHAR(200)" + ")";

    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new MemDbException(e);
    }
  }

  /**
   * Called by the functional test to drop the person table.
   * 
   * @throws MemDbException Thrown if an error occurs creating or executing the prepared statement.
   */
  public void dropPersonTable() {
    String sql = "" + "DROP TABLE person";

    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new MemDbException(e);
    }
  }

  /**
   * This adds a {@link Person} record to the person table.
   * 
   * @param name The person name
   * @throws MemDbException Thrown if an error occurs creating or executing the prepared statement.
   */
  public void addPerson(String name) {
    String sql = "INSERT INTO person (person_name) VALUES (?)";

    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setString(1, name);
      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new MemDbException(e);
    }
  }

  /**
   * This searches for a {@link Person} record that has a matching name.
   * 
   * @param nameToFind The name to search for
   * @return An Optional containing the Person record if found or an empty Optional if not found.
   * @throws MemDbException Thrown if an error occurs creating or executing the prepared statement.
   */
  public Optional<Person> findName(String nameToFind) {
    String sql = "SELECT * FROM person WHERE person_name = ?";

    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
      Person person = null;

      stmt.setString(1, nameToFind);

      try (ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) {
          int pk = rs.getInt("person_pk");
          String name = rs.getString("person_name");

          person = new Person(pk, name);
        }
      }

      return Optional.ofNullable(person);

    } catch (SQLException e) {
      throw new MemDbException(e);
    }
  }

}
