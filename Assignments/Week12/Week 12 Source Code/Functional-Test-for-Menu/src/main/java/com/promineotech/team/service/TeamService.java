/**
 * 
 */
package com.promineotech.team.service;

import java.util.NoSuchElementException;
import com.promineotech.team.dao.TeamDao;
import com.promineotech.team.model.Team;

/**
 * This class is in the service layer (package). It is responsible for liaising between the controller (Main.java) and
 * the DAO layer ({@link TeamDao}.java). If there are any business rules to apply they are applied in this class.
 * 
 * @author Promineo
 *
 */
public class TeamService {
  private TeamDao teamDao = new TeamDao();

  /**
   * Call the {@link TeamDao} to create the tables.
   */
  public void createTables() {
    teamDao.createTables();
  }

  /**
   * Call the {@link TeamDao} to drop the tables.
   */
  public void dropTables() {
    teamDao.dropTables();
  }

  /**
   * Create a team given the team name. This just passes the team name to the {@link TeamDao}.
   * 
   * @param teamName The team name
   */
  public void createTeam(String teamName) {
    teamDao.createTeam(teamName);
  }

  /**
   * Find the Team record given the name.
   * 
   * @param teamName The team name
   * @return The team object if found
   * @throws NoSuchElementException Thrown if the team record identified by the team name is not in the database.
   */
  public Team findTeam(String teamName) {
    return teamDao.findTeam(teamName)
        .orElseThrow(() -> new NoSuchElementException("Team " + teamName + " is not found"));
  }

}
