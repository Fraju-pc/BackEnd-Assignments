/**
 * 
 */
package com.promineotech.weather;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * This class tests the mythical Weather News application by mocking the {@link WeatherNews} object so that a predefined
 * {@link Weather} object is returned to it instead of the random Weather object returned by the
 * {@link WackyWeatherService}. Since {@link WeatherNews#retrieveWeatherFromService()} is mocked, the
 * WackyWeatherService is not called during the test.
 * 
 * Note that the video said to use @ExtendWith(MockitoExtension.class). This is not needed for normal mocking. This is
 * only needed if annotation support for @Mock and @InjectMocks is used.
 * 
 * @author Promineo
 *
 */
class WeatherNewsTest {
  /*
   * This enum is used by the tests to indicate whether the alert methods should have been called. This approach is much
   * more readable than a true/false in the method call.
   */
  private enum Resolution {
    ALERTS_CALLED, ALERTS_NOT_CALLED
  };

  /**
   * This tests the scenario in which both a low and a high temperature alert is needed. It is difficult to test any
   * output from the calls to System.out.println in the class under test. So difficult, in fact, that we just record the
   * calls to {@link WeatherNews#alertHighTemp(Weather)} and {@link WeatherNews#alertLowTemp(Weather)}.
   */
  @Test
  void assertHighAndLowTempsTriggerAlerts() {
    // Given: a high and low weather event
    Weather weather = createHighLowWeatherEvent();
    WeatherNews news = createWeatherNewsMock(weather);

    // When: the weather is retrieved
    news.reportTheWeather();

    // Then: the alerts were triggered
    verifyAlertsCalled(weather, news, Resolution.ALERTS_CALLED);
  }

  /**
   * A comment probably isn't needed here because the test specification (method name) pretty much says it all. But,
   * anyway, this tests that the "normal" weather event does not trigger either the low or high temperature alerts.
   */
  @Test
  void assertNormalTempsDoNotTriggerAlerts() {
    // Given: a high and low weather event
    Weather weather = createNormalWeatherEvent();
    WeatherNews news = createWeatherNewsMock(weather);

    // When: the weather is retrieved
    news.reportTheWeather();

    // Then: the alerts were triggered
    verifyAlertsCalled(weather, news, Resolution.ALERTS_NOT_CALLED);
  }

  /**
   * This method creates a weather event that should not trigger either a high or low temperature alert.
   * 
   * @return The weather event.
   */
  private Weather createNormalWeatherEvent() {
    String region = "Roberts, Idaho";
    Float high = WeatherNews.getHighAlertTrigger();
    Float low = WeatherNews.getLowAlertTrigger();
    String report = region + " high=" + high + ", low=" + low;

    return new Weather(region, high, low, report);
  }

  /**
   * This method validates that either both high and low alerts were triggered or that neither of them were triggered.
   * 
   * @param weather The weather event created by the test
   * @param news The object under test
   * @param alertsCalled If this value is {@link Resolution#ALERTS_CALLED} both the high and low weather alert methods
   *        should have been called one time. If this value is {@link Resolution#ALERTS_NOT_CALLED} then the alert
   *        methods should not been called.
   */
  private void verifyAlertsCalled(Weather weather, WeatherNews news, Resolution alertsCalled) {
    int timesCalled = Resolution.ALERTS_CALLED == alertsCalled ? 1 : 0;

    /*
     * The verify method is used to validate that the method was called with the correct Weather parameter. The times
     * method used as a parameter tells Mockito that the alert method should have been called zero or one time. Mockito
     * will throw an exception if the value is not correct.
     */
    verify(news, times(timesCalled)).alertHighTemp(weather);
    verify(news, times(timesCalled)).alertLowTemp(weather);
  }

  /**
   * This creates a mock on a newly created {@link WeatherNews} object. We want the WeatherNews object to respond
   * normally to all method calls except for the ones we program here: {@link WeatherNews#retrieveWeatherFromService()},
   * {@link WeatherNews#alertHighTemp(Weather)} and {@link WeatherNews#alertLowTemp(Weather)}. This is why
   * {@link Mockito#spy(Class)} is used instead of {@link Mockito#mock(Class)}.
   * 
   * @param weather This weather object is used by Mockito to replace the Weather object that would have been returned
   *        by a call to {@link WeatherNews#retrieveWeatherFromService()}.
   * @return The mocked news object.
   */
  private WeatherNews createWeatherNewsMock(Weather weather) {
    WeatherNews news = spy(new WeatherNews());

    doReturn(weather).when(news).retrieveWeatherFromService();
    lenient().doNothing().when(news).alertHighTemp(weather);
    lenient().doNothing().when(news).alertLowTemp(weather);

    return news;
  }

  /** This returns a {@link Weather} object that should trigger both a high and low temperature alert.
   * @return The Weather object.
   */
  private Weather createHighLowWeatherEvent() {
    String region = "Rigby, Idaho";
    Float high = WeatherNews.getHighAlertTrigger() + 0.1F;
    Float low = WeatherNews.getLowAlertTrigger() - 0.1F;
    String report = region + " high=" + high + ", low=" + low;

    return new Weather(region, high, low, report);
  }

}
