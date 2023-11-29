/**
 * 
 */
package com.promineotech.team.model;

/**
 * This is the team object. It is not a very robust abstraction, having only an ID (primary key value) and a name. There
 * are no getters on this class so the object is immutable once created.
 * 
 * @author Promineo
 *
 */
public class Team {
  private int teamPK;
  private String teamName;

  /**
   * Constructor that sets all instance variables.
   */
  public Team(int teamPK, String teamName) {
    this.teamPK = teamPK;
    this.teamName = teamName;

  }

  /**
   * @return the teamPK
   */
  public int getTeamPK() {
    return teamPK;
  }

  /**
   * @return the teamName
   */
  public String getTeamName() {
    return teamName;
  }

  /**
   * Representation of the team as a String.
   */
  @Override
  public String toString() {
    return "Team [teamPK=" + teamPK + ", teamName=" + teamName + "]";
  }

}
