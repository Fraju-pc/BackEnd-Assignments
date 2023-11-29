# The weather news service

This project demonstrates a "correct" way to write unit tests. It's not the only way, but it's a good way. You should remember the two rules of tests:

1. Write tests as high-level as you can. It is better to test the functionality of an entire application or feature instead of testing a single method. This is true even if you test all the methods singly. This is because it is possible to test all the methods individually only to find that the application doesn't work when tested as a whole. Unit tests test single methods. It is definitely worth creating unit tests for complex methods. However, if your method is  _too_  complex it may defy testing and should be refactored. Functional tests (also called integration tests) test the results (or outcomes) of an application or feature. This type of test is generally preferred over unit tests because at the end of the day you will know that your application works as a whole.

1. Write tests as soon as you can. It's best to write the test  _before_  coding the application if you can (Test-Driven Development or Behavior-Driven Development). If not possible, the next best thing is to write the test as you are developing the application code. If not possible, the next best thing is to write tests immediately after writing the application code. The worst case is not to write tests at all. Almost as bad is to let someone else write tests for the code that  _you_  wrote! Only  _you_  know why you made the decisions you did and only  _you_  can write a meaningful test.

# Testing considerations

If you are testing the result of a method or feature, use simple assertions. This is as true for functional tests as it is for unit tests.

```
public int apply(int a, int b, OperationType type) {
  switch(type) {
    case ADD:
      return add(a, b);
      
    case SUBTRACT:
      return subtract(a, b);
      
    ...
  }
}
```

The above method calls various other methods internally. You absolutely do not care about that. It may call another service accross the planet somewhere. You do not care about that either (although making a network call in a test is extremely risky so you may want to reconsider this). The point is that you should only care about the result of the method. Here are some good tests:

```
assertThat(apply(2, 4, OperationType.ADD)).isEqualTo(6);
assertThat(apply(2, 4, OperationType.SUBTRACT)).isEqualTo(-2);
// and etcetera
```

If the method being tested calls another method in another class, mock the method and have it return something that you can make an assertion on. You want to control the inputs so that you can verify the outcomes. This will also help with the my-test-called-a-server-on-the-other-side-of-the-planet-and-it-wasn't-online-so-my-test-broke syndrome. You definitely want to mock (also called stub-out) that service call. You can use Mockito.mock, which will stub-out all the methods in the object/interface, or Mockito.spy, which will let the mocked object return real results from methods that are not mocked.

In general,

* Use Mockito.spy to mock the object under test, and
* Use Mockito.mock to mock an object that is  _used_  by the object under test

Testing is not as much of an arcane art as you might think when you're just starting out. Writing good and clear tests is easy when you have written good and clear application code. Forcing yourself to write good tests is also forcing you to write good application code. That's the way it works.

# Use given/when/then

Martin Fowler of Thoughtworks developed the [Given/When/Then](https://martinfowler.com/bliki/GivenWhenThen.html) style of representing tests. Following this style will improve the organization and readability of your tests. In Java, Given/When/Then headings are comments so a good style is for them to be the only comments in the test. If you need to provide additional comments, break out the code into another method and put the comment in that method. The point is to make the test as readable as possible.

The test method name should be the title of the test, often called the specification. Inside the test method, the "given" comment should describe the preconditions of the test, the "when" comment should describe  _what_  is being tested, and the "then" comment should describe the expected outcome(s). The test name and comments should give any reader a clear understanding of what the test is about.

Here is an example:

```
@Test
void verifyThatAPersonWithEnoughMoneyCanBuyAHamburger() {
  // GIVEN: that a hamburger costs $7.95 AND the person has a 10 dollar bill
  (set up person here)
  
  // WHEN: the hamburger is purchased
  (perform transaction)
  
  // THEN: the person has a hamburger AND $2.05 left over. 
  (assert on hamburger and change)
}
```

# About the project

## Maven project

This is a Maven project. The build is controlled by pom.xml, the POM, or Project Object Model file. You don't need to know about the details of the POM file at this point. It is used to conveniently add project dependencies (Java JAR files that are used in the project). It avoids having to manually add a JAR (like MySQL Connector/J) into the project. Maven takes care of downloading the project dependencies automatically.

However, because this is a Maven project, it is arranged a little differently than you may be used to. Maven considers these directories to be important:

### The source directory

All source is kept under the  _src_  directory of the project. 

### The target directory

All built stuff (project artifacts) is kept under the  _target_  directory of the project. Maven has a clean command (called a goal) that simply deletes everything in the  _target_  directory so don't put anything you want to keep there that can't be rebuilt.

### Application source files

Application source files, like all .java files, are stored in the  _src/main/java_  directory and subdirectories (packages). Non-Java source files, like configuration files are stored in  _src/main/resources_  and subdirectories. Maven knows how to compile .java files and knows where to put resource files so that they can be used by a Java application.

### Test source files

Test source files are simply Java source files that don't wind up in the built artifact (like a JAR file). Maven will automatically build and run Java test files if they are found in  _src/test/java_  and subdirectories (packages). Any resources used in the tests (JSON, XML, configuration files, etc.) should be placed in the  _src/test/resources_  folder or in subdirectories off of this folder.

## Where to look for the good stuff

The main test developed in the video is com.promineotech.weather.WeatherNewsTest.java. This is found under the  _src/test/java_  folder. All the other application code is in the same package under the  _src/main/java_  folder. 

You will find the following application files:

* WeatherNews.java - this contains the main method. It produces a "weather report" when run.
* Weather.java - this is an immutable object that contains information about the weather and the report.
* WeatherService.java - this is an interface defining the weather-related functions.
* WackyWeatherService.java - this is the weather randomizing service that is called by the WeatherNews object.