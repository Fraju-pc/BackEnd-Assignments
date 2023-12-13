// Copyright (c) 2023 by Promineo Tech.

package spring.jdbc.demo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import spring.jdbc.demo.entity.AltName;
import spring.jdbc.demo.entity.Breed;
import spring.jdbc.demo.entity.Category;

/**
 * This class uses Spring JDBC to retrieve all bunny rows along with any
 * alternate names and breed categories.
 * 
 * @author Promineo
 *
 */
@Component
public class BreedDao {

  /**
   * The NamedParameterJdbcTemplate allows us to perform queries on the database
   * tables.
   */
  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;

  /**
   * Retrieve all breed rows.
   * 
   * @return The list of breed rows.
   */
  public List<Breed> findAll() {
    /* Fetch all breed rows. */
    List<Breed> breeds = findAllBreeds();

    /* Add in any alt names and breed categories. */
    for(Breed breed : breeds) {
      breed.getAltNames().addAll(findAllAltNames(breed.getBreedId()));
      breed.getCategories().addAll(findCategories(breed.getBreedId()));
    }

    return breeds;
  }

  /**
   * Retrieve a list of breed categories given the breed ID.
   * 
   * @param breedId The breed ID.
   * @return A list of breed categories.
   */
  private List<Category> findCategories(Long breedId) {
    /*
     * In the NamedParameterJdbcTemplate, parameters are specified as
     * ":parameter_name". Then, in the parameter map, add "parameter_name" as
     * the key and the parameter as the value. This prevents SQL injection
     * attacks.
     */
    String sql = """
        SELECT *
        FROM category
        JOIN breed_category bc USING(category_id)
        WHERE bc.breed_id = :breed_id
        """;

    Map<String, Object> parms = Map.of("breed_id", breedId);

    return jdbcTemplate.query(sql, parms, (rs, rowNum) -> {
      Category category = new Category();

      category.setCategoryId(rs.getLong("category_id"));
      category.setCategoryName(rs.getString("category_name"));

      return category;
    });
  }

  /**
   * Retrieves any alternate breed names given the breed ID.
   * 
   * @param breedId The breed ID.
   * @return A (possibly empty) list of alternate names.
   */
  private List<AltName> findAllAltNames(Long breedId) {
    String sql = """
        SELECT *
        FROM alt_name
        WHERE breed_id = :breed_id
        """;

    Map<String, Object> parms = Map.of("breed_id", breedId);

    return jdbcTemplate.query(sql, parms, (rs, rowNum) -> {
      AltName altName = new AltName();

      altName.setAlternateId(rs.getLong("alternate_id"));
      altName.setBreedId(rs.getLong("breed_id"));
      altName.setAlternateName(rs.getString("alternate_name"));

      return altName;
    });
  }

  /**
   * Retrieve all breed rows from the breed table.
   * 
   * @return The breed rows.
   */
  private List<Breed> findAllBreeds() {
    String sql = """
        SELECT *
        FROM breed
        """;

    /* Uncomment the code below to retrieve breed rows with a RowMapper. */
    // return jdbcTemplate.query(sql, new RowMapper<>() {
    // @Override
    // public Breed mapRow(ResultSet rs, int rowNum) throws SQLException {
    // Breed breed = new Breed();
    //
    // breed.setBreedId(rs.getLong("breed_id"));
    // breed.setBreedName(rs.getString("breed_name"));
    // breed.setDescription(rs.getString("description"));
    //
    // return breed;
    // }
    // });

    /*
     * This uses a Lambda expression as a substitute for the RowMapper. It it
     * mostly the same as if a RowMapper is used. But the Lambda expression does
     * not create a separate .class file like the RowMapper does. For this
     * reason, Lambda expressions are considered lightweight solutions.
     * 
     * Note that this used the full form of the Lambda expression because it is
     * a multi-line Lambda. Therefore the curly braces and return statement are
     * required.
     */
    return jdbcTemplate.query(sql, (rs, rowNum) -> {
      Breed breed = new Breed();

      breed.setBreedId(rs.getLong("breed_id"));
      breed.setBreedName(rs.getString("breed_name"));
      breed.setDescription(rs.getString("description"));

      return breed;
    });
  }
}
