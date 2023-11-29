# Behavior-Driven Development (BDD) demo

This application demonstrates how to write a results-based test that verifies the results of a service that talks with a database through a Data Access Object (DAO) layer. Writing the test first caused a great deal of application code to be written to satisfy the test. Since the test interacted with the service layer, classes in the service layer, and the DAO layer had to be written. A connection needed to be made with the database and SQL written to communicate with it. All of this was tested with a single test method.

Unit tests validate single methods. Functional tests validate entire features or applications. BDD tests verify application functionality. These tests are to be preferred over unit tests if possible because they validate large portions of the application.

# About the project

## Maven project

This project is controlled by Maven. See the Project Object Model (POM) file, pom.xml for a list of project dependencies. Note the h2database dependency. This adds an in-memory database used to write code to save people to the database. People are very simple abstractions in this application, having only a primary key identifier and a name. The business rules applied in the service change a lowercase name to a capitalized proper name. The rules are not applied very strictly -- the purpose was to show how this could be done and not how to bulletproof it.

## Where's the stuff?

The driving test class is under  _src/test/java_  in the com.promineotech.person.service package. The class file is PersonServiceTest.java.

All the application code is under  _src/main/java_ . The service PersonService.java is in the com.promineotech.person.service package. The DAO class PersonDao.java is in the com.promineotech.person.dao package. The Person class is in the com.promineotech.person.model package.