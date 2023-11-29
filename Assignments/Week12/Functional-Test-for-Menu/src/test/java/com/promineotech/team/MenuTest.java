/**
 * 
 */
package com.promineotech.team;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import java.util.Scanner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import com.promineotech.team.model.Team;

/**
 * This test class was written before any application code was written so as to demonstrate how the test code can drive
 * application development. This class contains functional tests, so is an example of Behavior-Driven Development in
 * Java. Classes that read and write to System.out or to loggers as part of their normal function (i.e., not simply
 * logging) are extremely difficult to test. It turns out that it <em>is</em> possible to mock the input device (the
 * keyboard). But testing things like printing instructions, menu selections, etc. is not really worth the effort. That
 * will need to be the realm of UAT testers, not automated test code.
 * 
 * @author Promineo
 *
 */
class MenuTest {
  private Menu menu;

  /**
   * This method is called by JUnit prior to running each test. It creates a new {@link Menu} object, along with the
   * tables needed to store team and player information.
   */
  @BeforeEach
  void setUp() {
    menu = new Menu();
    menu.createTables();
  }

  /**
   * JUnit calls this method after each test is run. This method drops the tables used by the menu application.
   */
  @AfterEach
  void tearDown() {
    menu.dropTables();
  }

  /**
   * This tests that when the menu is displayed it will then exit properly. This is a small test that resulted in the
   * creation of a substantial amount of application code. This demonstrates the power of functional tests. Small tests
   * can test large parts of an application.
   * 
   * This works by substituting the Scanner created by the application with a Scanner supplied by the test. The Scanner
   * created by the application uses the keyboard as the input device. The Scanner supplied by the test uses a String as
   * the input device. By using a command String it is possible to cause the application to behave in the desired manner
   * with no human input needed.
   * 
   * The menu object used by the test is created in the {@link #setUp()} method. It should function normally in all
   * method calls except for the one that returns a Scanner. Therefore {@link Mockito#spy(Class)} is used to mock the
   * Menu object. After spy is called, calls made to the original Menu object will not reflect the programming used on
   * the spied object. Therefore, care must be taken to not call the original object after calling spy.
   */
  @Test
  void assertThatExitCommandWorksCorrectly() {
    // Given: an exit command
    String cmd = Menu.EXIT + "\n";
    Menu testMenu = createMockedMenu(cmd);

    // When: the menu is displayed
    testMenu.displayMenu();

    // Then: no exception is thrown and the test does not just sit there for 5 or so billion years.
  }

  /**
   * This method verifies that a team can be created in the database. It passes a "create team" command to the faux
   * Scanner and then supplies the team name. This is followed by the exit command that has already been shown to work.
   * 
   * The team creation is verified by retrieving the Team object from the database. When the Scanner is created, it is
   * substituted for the Scanner that would have been created by the application using a mocked method call (spy).
   * Testing that the correct team is returned from the database is simply done with an assertion that the team name is
   * correct.
   * 
   * This combination of mocking and assertions is reflective of the type of behavior that is desired in the test.
   * Mocking is used to override a method or class. Assertions are used to test a result. Both techniques are commonly
   * used in all types of testing, although it may be more common to use mocking in unit tests vs. functional tests.
   */
  @Test
  void assertThatATeamIsCreated() {
    // Given: a create command
    String teamName = "Gnarly Tigers";
    String cmd = buildCreateTeamCommand(teamName);
    Menu testMenu = createMockedMenu(cmd);

    // When: the menu is displayed
    testMenu.displayMenu();

    // Then: the team is added to the database.
    Team team = testMenu.findTeam(teamName);
    assertThat(team.getTeamName()).isEqualTo(teamName);
  }

  /**
   * Generate the create team command using the supplied team name.
   * 
   * @param teamName The team name
   * @return The command string
   */
  private String buildCreateTeamCommand(String teamName) {
    String cmd = Menu.CREATE_TEAM + "\n";
    cmd += teamName + "\n";
    cmd += Menu.EXIT + "\n";
    return cmd;
  }

  /**
   * Create the mocked menu object that uses a Scanner that reads from the given command String.
   * 
   * @param cmd The command String
   * @return The mocked menu object
   */
  private Menu createMockedMenu(String cmd) {
    Scanner scanner = new Scanner(cmd);

    Menu testMenu = spy(menu);
    doReturn(scanner).when(testMenu).createScanner();
    return testMenu;
  }

}
