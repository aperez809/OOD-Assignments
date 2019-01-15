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

  @Test
  public void testLowerCaseTSpecifierCompact() {
    assertEquals("3600",
            sec(3600).format("%t"));
  }

  @Test
  public void testLowerCaseHSpecifierCompact() {
    assertEquals("1",
            sec(3600).format("%h"));
    assertEquals("10",
            sec(36000).format("%h"));
  }

  @Test
  public void testLowerCaseMSpecifierCompact() {
    assertEquals("1",
            sec(60).format("%m"));
    assertEquals("10",
            sec(600).format("%m"));
  }

  @Test
  public void testLowerCaseSSpecifierCompact() {
    assertEquals("1",
            sec(1).format("%s"));
    assertEquals("10",
            sec(10).format("%s"));
    assertEquals("1:1",
            sec(61).format("%m:%s"));
  }

  @Test
  public void testPercentSpecifierCompact() {
    assertEquals("%",
            sec(1).format("%%"));
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
