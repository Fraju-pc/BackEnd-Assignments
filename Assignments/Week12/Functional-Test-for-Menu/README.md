# Functional test for menu

This project demonstrates how to write a functional test for a menu application. It is difficult to write a useful functional test for a class that uses a keyboard as an input device and writes to stdout as the output device. The input device can be mocked, as it turns out, but there is no convenient way to mock the output device. We are left with examining data that is written to the database to perform test validations. This is not an inappropriate situation so tests  _can_  be written that accurately and adequately test the menu functions.

The tests use a combination of mocking and assertions to validate the menu application. As the menu application is potentially very large and this is a teaching demonstration, only a couple of use cases are actually tested.

# The project

## Maven project

This project is controlled by Maven. See the Project Object Model (POM) file, pom.xml for a list of project dependencies. Note the h2database dependency. This adds an in-memory database used to write code to save teams to and retrieve teams from the database. 

## Where's my stuff?

The driving test is in  _src/test/java_  in the com.promineotech.team package. Other application files are in  _src/main/java_ . The main application menu is in com.promineotech.team.Menu.java.