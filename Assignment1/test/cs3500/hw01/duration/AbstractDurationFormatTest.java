package cs3500.hw01.duration;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/** Tests for the format method of {@link Duration}s. 
    Add your tests to this class to assure that your format 
    method works properly
*/
public abstract class AbstractDurationFormatTest {
  @Test
  public void formatExample1() {
    assertEquals("4 hours, 0 minutes, and 9 seconds",
                  hms(4, 0, 9)
                    .format("%h hours, %m minutes, and %s seconds"));
  }

  @Test
  public void formatExample2() {
    assertEquals("4:05:17",
                  hms(4, 5, 17).format("%h:%M:%S"));
  }


  // ADD MORE TESTS HERE
  // Your tests must only use hms(...) and sec(...) to construct new Durations
  // and must *not* directly say "new CompactDuration(...)" or
  // "new HmsDuration(...)"

  @Test(expected = IllegalArgumentException.class)
  public void testNullStringPassedCompact() {
    sec(3600).format(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIncompatibleFormatSpecifierCompact() {
    sec(3600).format("%g");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testUpperCaseTFormatSpecifierCompact() {
    sec(3600).format("%T");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBogusIncompleteFormatSpecifierCompact() {
    sec(3600).format("%");
  }

  @Test
  public void testNoFormatSpecifierProvidedCompact() {
    assertEquals("Hello!",
            sec(3600).format("Hello!"));
  }

  @Test
  public void testLowerCaseTSpecifierCompact() {
    assertEquals("3600",
            sec(3600).format("%t"));
  }

  @Test
  public void testLowerCaseHSpecifierSingleDigitCompact() {
    assertEquals("1",
            sec(3600).format("%h"));
  }

  @Test
  public void testLowerCaseHSpecifierMultiDigitCompact() {
    assertEquals("10",
            sec(36000).format("%h"));
  }

  @Test
  public void testLowerCaseHSpecifierStackedCompact() {
    assertEquals("1010",
            sec(36000).format("%h%h"));
  }

  @Test
  public void testUpperCaseHSpecifierSingleDigitCompact() {
    assertEquals("01",
            sec(3600).format("%H"));
  }

  @Test
  public void testUpperCaseHSpecifierMultiDigitCompact() {
    assertEquals("10",
            sec(36000).format("%H"));
  }

  @Test
  public void testUpperCaseHSpecifierStackedCompact() {
    assertEquals("1010",
            sec(36000).format("%H%H"));
  }

  @Test
  public void testLowerCaseMSpecifierSingleDigitCompact() {
    assertEquals("1",
            sec(60).format("%m"));
  }

  @Test
  public void testLowerCaseMSpecifierMultiDigitCompact() {
    assertEquals("10",
            sec(600).format("%m"));
  }

  @Test
  public void testLowerCaseMSpecifierOverflowCompact() {
    assertEquals("0",
            sec(3601).format("%m"));
  }

  @Test
  public void testLowerCaseMSpecifierSingleDigitStackedCompact() {
    assertEquals("11",
            sec(60).format("%m%m"));
  }

  @Test
  public void testLowerCaseMSpecifierMultiDigitStackedCompact() {
    assertEquals("1010",
            sec(600).format("%m%m"));
  }

  @Test
  public void testLowerCaseMSpecifierOverflowStackedCompact() {
    assertEquals("00",
            sec(3601).format("%m%m"));
  }

  @Test
  public void testUpperCaseMSpecifierSingleDigitCompact() {
    assertEquals("01",
            sec(60).format("%M"));
  }

  @Test
  public void testUpperCaseMSpecifierMultiDigitCompact() {
    assertEquals("10",
            sec(600).format("%M"));
  }

  @Test
  public void testUpperCaseMSpecifierOverflowCompact() {
    assertEquals("00",
            sec(3601).format("%M"));
  }

  @Test
  public void testUpperCaseMSpecifierSingleDigitStackedCompact() {
    assertEquals("0101",
            sec(60).format("%M%M"));
  }

  @Test
  public void testUpperCaseMSpecifierMultiDigitStackedCompact() {
    assertEquals("1010",
            sec(600).format("%M%M"));
  }

  @Test
  public void testUpperCaseMSpecifierOverflowStackedCompact() {
    assertEquals("0000",
            sec(3601).format("%M%M"));
  }

  @Test
  public void testLowerCaseSSpecifierSingleDigitCompact() {
    assertEquals("1",
            sec(1).format("%s"));
  }

  @Test
  public void testLowerCaseSSpecifierMultiDigitCompact() {
    assertEquals("10",
            sec(10).format("%s"));
  }

  @Test
  public void testLowerCaseSSpecifierOverflowCompact() {
    assertEquals("1",
            sec(61).format("%s"));
  }

  @Test
  public void testLowerCaseSSpecifierStackedCompact() {
    assertEquals("1010",
            sec(10).format("%s%s"));
  }

  @Test
  public void testLowerCaseSSpecifierOverflowStackedCompact() {
    assertEquals("11",
            sec(61).format("%s%s"));
  }

  @Test
  public void testUpperCaseSSpecifierSingleDigitCompact() {
    assertEquals("01",
            sec(1).format("%S"));
  }

  @Test
  public void testUpperCaseSSpecifierMultiDigitCompact() {
    assertEquals("10",
            sec(10).format("%S"));
  }

  @Test
  public void testUpperCaseSSpecifierOverflowCompact() {
    assertEquals("01",
            sec(61).format("%S"));
  }

  @Test
  public void testUpperCaseSSpecifierStackedCompact() {
    assertEquals("1010",
            sec(10).format("%S%S"));
  }

  @Test
  public void testUpperCaseSSpecifierOverflowStackedCompact() {
    assertEquals("0101",
            sec(61).format("%S%S"));
  }

  @Test
  public void testPercentSpecifierAloneCompact() {
    assertEquals("%",
            sec(104328602).format("%%"));

  }

  @Test
  public void testPercentSpecifierMultiplePercentsCompact() {
    assertEquals("%t",
            sec(10).format("%%t"));
  }



  @Test(expected = IllegalArgumentException.class)
  public void testNullStringPassedHms() {
    sec(3600).format(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIncompatibleFormatSpecifierHms() {
    sec(3600).format("%g");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testUpperCaseTFormatSpecifierHms() {
    sec(3600).format("%T");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBogusIncompleteFormatSpecifierHms() {
    sec(3600).format("%");
  }

  @Test
  public void testNoFormatSpecifierProvidedHms() {
    assertEquals("Hello!",
            sec(3600).format("Hello!"));
  }

  @Test
  public void testLowerCaseTSpecifierHms() {
    assertEquals("3600",
            sec(3600).format("%t"));
  }

  @Test
  public void testLowerCaseHSpecifierSingleDigitHms() {
    assertEquals("1",
            sec(3600).format("%h"));
  }

  @Test
  public void testLowerCaseHSpecifierMultiDigitHms() {
    assertEquals("10",
            sec(36000).format("%h"));
  }

  @Test
  public void testLowerCaseHSpecifierStackedHms() {
    assertEquals("1010",
            sec(36000).format("%h%h"));
  }

  @Test
  public void testUpperCaseHSpecifierSingleDigitHms() {
    assertEquals("01",
            sec(3600).format("%H"));
  }

  @Test
  public void testUpperCaseHSpecifierMultiDigitHms() {
    assertEquals("10",
            sec(36000).format("%H"));
  }

  @Test
  public void testUpperCaseHSpecifierStackedHms() {
    assertEquals("1010",
            sec(36000).format("%H%H"));
  }

  @Test
  public void testLowerCaseMSpecifierSingleDigitHms() {
    assertEquals("1",
            sec(60).format("%m"));
  }

  @Test
  public void testLowerCaseMSpecifierMultiDigitHms() {
    assertEquals("10",
            sec(600).format("%m"));
  }

  @Test
  public void testLowerCaseMSpecifierOverflowHms() {
    assertEquals("0",
            sec(3601).format("%m"));
  }

  @Test
  public void testLowerCaseMSpecifierSingleDigitStackedHms() {
    assertEquals("11",
            sec(60).format("%m%m"));
  }

  @Test
  public void testLowerCaseMSpecifierMultiDigitStackedHms() {
    assertEquals("1010",
            sec(600).format("%m%m"));
  }

  @Test
  public void testLowerCaseMSpecifierOverflowStackedHms() {
    assertEquals("00",
            sec(3601).format("%m%m"));
  }

  @Test
  public void testUpperCaseMSpecifierSingleDigitHms() {
    assertEquals("01",
            sec(60).format("%M"));
  }

  @Test
  public void testUpperCaseMSpecifierMultiDigitHms() {
    assertEquals("10",
            sec(600).format("%M"));
  }

  @Test
  public void testUpperCaseMSpecifierOverflowHms() {
    assertEquals("00",
            sec(3601).format("%M"));
  }

  @Test
  public void testUpperCaseMSpecifierSingleDigitStackedHms() {
    assertEquals("0101",
            sec(60).format("%M%M"));
  }

  @Test
  public void testUpperCaseMSpecifierMultiDigitStackedHms() {
    assertEquals("1010",
            sec(600).format("%M%M"));
  }

  @Test
  public void testUpperCaseMSpecifierOverflowStackedHms() {
    assertEquals("0000",
            sec(3601).format("%M%M"));
  }

  @Test
  public void testLowerCaseSSpecifierSingleDigitHms() {
    assertEquals("1",
            sec(1).format("%s"));
  }

  @Test
  public void testLowerCaseSSpecifierMultiDigitHms() {
    assertEquals("10",
            sec(10).format("%s"));
  }

  @Test
  public void testLowerCaseSSpecifierOverflowHms() {
    assertEquals("1",
            sec(61).format("%s"));
  }

  @Test
  public void testLowerCaseSSpecifierStackedHms() {
    assertEquals("1010",
            sec(10).format("%s%s"));
  }

  @Test
  public void testLowerCaseSSpecifierOverflowStackedHms() {
    assertEquals("11",
            sec(61).format("%s%s"));
  }

  @Test
  public void testUpperCaseSSpecifierSingleDigitHms() {
    assertEquals("01",
            sec(1).format("%S"));
  }

  @Test
  public void testUpperCaseSSpecifierMultiDigitHms() {
    assertEquals("10",
            sec(10).format("%S"));
  }

  @Test
  public void testUpperCaseSSpecifierOverflowHms() {
    assertEquals("01",
            sec(61).format("%S"));
  }

  @Test
  public void testUpperCaseSSpecifierStackedHms() {
    assertEquals("1010",
            sec(10).format("%S%S"));
  }

  @Test
  public void testUpperCaseSSpecifierOverflowStackedHms() {
    assertEquals("0101",
            sec(61).format("%S%S"));
  }

  @Test
  public void testPercentSpecifierAloneHms() {
    assertEquals("%",
            sec(104328602).format("%%"));

  }

  @Test
  public void testPercentSpecifierMultiplePercentsHms() {
    assertEquals("%t",
            sec(10).format("%%t"));
  }

  @Test
  public void testOverlappingPercentHms() {
    assertEquals("%h:05:17",
            hms(4, 5, 17).format("%%h:%M:%S"));
  }

  @Test
  public void testOverlappingPercentSec() {
    assertEquals("%h:00:0",
            sec(3600).format("%%h:%M:%s"));
  }




  

  /*
    Leave this section alone: It contains two abstract methods to
    create Durations, and concrete implementations of this testing class
    will supply particular implementations of Duration to be used within 
    your tests.
   */
  /**
   * Constructs an instance of the class under test representing the duration
   * given in hours, minutes, and seconds.
   *
   * @param hours the hours in the duration
   * @param minutes the minutes in the duration
   * @param seconds the seconds in the duration
   * @return an instance of the class under test
   */
  protected abstract Duration hms(int hours, int minutes, int seconds);

  /**
   * Constructs an instance of the class under test representing the duration
   * given in seconds.
   *
   * @param inSeconds the total seconds in the duration
   * @return an instance of the class under test
   */
  protected abstract Duration sec(long inSeconds);

  public static final class HmsDurationTest extends AbstractDurationFormatTest {
    @Override
    protected Duration hms(int hours, int minutes, int seconds) {
      return new HmsDuration(hours, minutes, seconds);
    }

    @Override
    protected Duration sec(long inSeconds) {
      return new HmsDuration(inSeconds);
    }
  }

  public static final class CompactDurationTest extends AbstractDurationFormatTest {
    @Override
    protected Duration hms(int hours, int minutes, int seconds) {
      return new CompactDuration(hours, minutes, seconds);
    }

    @Override
    protected Duration sec(long inSeconds) {
      return new CompactDuration(inSeconds);
    }
  }
}
