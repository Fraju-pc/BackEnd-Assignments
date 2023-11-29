# Test-Driven Development (TDD)

Test-Driven Development is simply unit testing in which the test is written prior to writing the application code. It is also known as red/green/refactor. This is because the major Java test frameworks like TestNG and JUnit have a status bar that shows green if all tests pass. It shows red if one or more tests fail.

In red/green/refactor, a partial unit test is written. This causes the status bar to turn red because there is no supporting application code. Then, just enough application code is written to pass the test and turn the status bar green. The test is then refactored to expect more from the application code, which turns the status bar red again. This process is repeated until the test is complete.

When two people do TDD together, it is called eXtreme Programming (XP). In eXtreme Programming, one person is the tester, who writes the unit test. The other is the application programmer, who writes the application code. In the original vision, the two would stand or sit side-by-side and share a single keyboard between them. The tester would write some test code causing the test to fail and the status bar to turn red. The tester then passes the keyboard over to the application coder, who writes just enough application code to pass the test and turn the status bar green. The process is then repeated until the test(s), feature(s) and application are complete. Periodically (every hour or two) the two coders switch roles.

This sounds counterintuitive but the two working together can produce code of higher quality and correctness than two people working independently. This increases speed of development, lowers defects, and decreases the overall cost of development.

eXtreme Programming is a part of the Agile development process. As the two coders talk with each other, the immediate design emerges in a natural manner.

# About the project

## Maven project

This is a Maven project. As such, all application source code is found under  _src/main/java_  and all test code is found under  _src/test/java_ .

## Where do I find stuff?

The test class is found in  _src/test/java_  in the com.promineotech.tdd package. The test class file is in TddDemoTest.java.

The class under test is found in  _src/main/java_  in the com.promineotech.tdd package. The class being tested's source file is TddDemo.java.