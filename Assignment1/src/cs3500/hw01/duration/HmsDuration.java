package cs3500.hw01.duration;

/**
 * Durations represented as hours, minutes, and seconds.
 */
public final class HmsDuration extends AbstractDuration {
  /**
   * Constructs a duration in terms of its length in hours, minutes, and
   * seconds.
   *
   * @param hours the number of hours
   * @param minutes the number of minutes
   * @param seconds the number of seconds
   * @throws IllegalArgumentException if any argument is negative
   */
  public HmsDuration(int hours, int minutes, int seconds) {
    this(inSeconds(hours, minutes, seconds));
    ensureHms(hours, minutes, seconds);
  }

  /**
   * Constructs a duration in terms of its length in seconds.
   *
   * @param inSeconds the number of seconds (non-negative)
   * @throws IllegalArgumentException {@code inSeconds} is negative
   */
  public HmsDuration(long inSeconds) {
    if (inSeconds < 0) {
      throw new IllegalArgumentException("must be non-negative");
    }

    seconds = secondsOf(inSeconds);
    minutes = minutesOf(inSeconds);
    hours   = hoursOf(inSeconds);
  }

  private final int hours;
  private final int minutes;
  private final int seconds;

  @Override
  protected AbstractDuration fromSeconds(long seconds) {
    return new HmsDuration(seconds);
  }

  @Override
  public long inSeconds() {
    return inSeconds(hours, minutes, seconds);
  }

  @Override
  public String asHms() {
    return asHms(hours, minutes, seconds);
  }

  /**
   * Constructs a String representation of a unit of time
   * as determined by the format specifier passed to it.
   *
   * @param s the {@code char} determining the unit of time to be inserted
   * @throws IllegalArgumentException {@code s} is not a compatible specifier
   */
  public String formatHelper(char s) {

    boolean isUpper = Character.isUpperCase(s);

    if (s == 't') {
      return Long.toString(this.inSeconds());
    }
    else if (s == '%') {
      return "%";
    } else if (s == 'h' || s == 'H') {
      return zeroPadding(isUpper, this.hours);
    } else if (s == 'm' || s == 'M') {
      return zeroPadding(isUpper, this.minutes);
    } else if (s == 's' || s == 'S') {
      return zeroPadding(isUpper,this.seconds % 60);
    } else {
      throw new IllegalArgumentException("Format specifier not recognized.");
    }
  }
}
