/**
 * 
 */
package com.promineotech.team;

import java.util.Scanner;
import com.google.common.annotations.VisibleForTesting;
import com.promineotech.team.model.Team;
import com.promineotech.team.service.TeamService;

/**
 * This class displays a menu that allows CRUD operations on teams and players. Only a single operation (create team)
 * has been implemented. This class contains a main method so it can be run from the command line or from within the
 * IDE.
 * 
 * @author Promineo
 *
 */
public class Menu {
  public static final int EXIT = 0;
  public static final int CREATE_TEAM = 1;

  private TeamService teamService = new TeamService();
  private Scanner scanner;

  /**
   * Entry point for a Java application. This method creates an object from the class and then calls the instance
   * method, run. The run method is then able to access any of the instance variables defined in the class.
   * 
   * @param args The command line arguments (ignored).
   */
  public static void main(String[] args) {
    new Menu().run();
  }

  /**
   * This method creates and drops the database tables. In between it calls the {@link #displayMenu()} method that loops
   * until an exit command is received.
   */
  private void run() {
    createTables();
    displayMenu();
    dropTables();
  }

  /**
   * This method displays the menu by printing the valid menu selections ({@link #printInstructions()}). It them gathers
   * input from the Scanner. If run from the command line or the IDE the Scanner is the keyboard input device. If run
   * from the functional tests, the Scanner is replaced by one that reads from a command String so that operations can
   * be performed without the need for human intervention.
   * 
   * The visibility of this method would normally be private but it is declared as package visibility so that the test,
   * which is in the same package, can call the method directly. The @VisibleForTesting annotation is used to tell
   * viewers that we didn't just make a mistake.
   */
  @VisibleForTesting
  void displayMenu() {
    boolean done = false;
    scanner = createScanner();

    while (!done) {
      printInstructions();

      int cmd = collectIntSelection("Enter your selection");

      switch (cmd) {
        case EXIT:
          done = exitMenu();
          break;

        case CREATE_TEAM:
          createTeam();
          break;

        default:
          System.out.println("You didn't understand??");
          break;
      }
    }
  }

  /**
   * This method collects a team name and calls the team service to create the team. The service will throw an exception
   * if the team cannot be created, so it's safe to print that the team was created successfully if an exception is not
   * thrown.
   */
  private void createTeam() {
    String teamName = collectStringSelection("Enter a team name");
    teamService.createTeam(teamName);
    System.out.println("\nTeam " + teamName + " created");
  }

  /**
   * This method retrieves a String input from the Scanner after printing a prompt message.
   * 
   * @param message The message to display.
   * @return The String retrieved from the Scanner.
   */
  private String collectStringSelection(String message) {
    System.out.print(message + ": ");
    String line = scanner.nextLine();
    System.out.println("\nYou entered " + line);
    return line;
  }

  /**
   * This method prints a line to the console saying that the menu is being exited.
   * 
   * @return Returns {@code true} to tell the menu to exit
   */
  private boolean exitMenu() {
    System.out.println("Exiting the menu");
    return true;
  }

  /**
   * This retrieves an integer selection from the Scanner. If a valid integer is not retrieved an exception is thrown by
   * the integer parser. This is caught and handled appropriately.
   * 
   * @param message A prompt message to display
   * @return The user choice as an integer, or -1 if a parse error occurred.
   */
  private int collectIntSelection(String message) {
    System.out.print(message + ": ");
    String line = scanner.nextLine();

    try {
      int selection = Integer.parseInt(line);
      System.out.println("\nYou entered selection " + selection);
      return selection;
    } catch (NumberFormatException e) {
      System.out.println("\n'" + line + "' is not valid!");
      return -1;
    }
  }

  /**
   * Print the valid menu selections for the user.
   */
  private void printInstructions() {
    System.out.println("\nThese are the valid choices:");
    System.out.println(EXIT + ") Exit the menu");
    System.out.println(CREATE_TEAM + ") Create a new team");

    System.out.println();
  }

  /**
   * Call the team service to create the tables.
   */
  public void createTables() {
    teamService.createTables();
  }

  /**
   * Call the team service to drop the tables.
   */
  public void dropTables() {
    teamService.dropTables();
  }

  /**
   * Create the scanner. This is mocked for the functional tests.
   */
  @VisibleForTesting
  Scanner createScanner() {
    return new Scanner(System.in);
  }

  /**
   * Call the team service to find a team given the team name
   * 
   * @param teamName The team name
   * @return The team object
   */
  public Team findTeam(String teamName) {
    return teamService.findTeam(teamName);
  }
}
