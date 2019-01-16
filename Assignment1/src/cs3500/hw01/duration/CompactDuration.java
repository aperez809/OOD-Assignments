package cs3500.hw01.duration;

/**
 * Durations represented compactly, with a range of 0 to
 * 2<sup>63</sup>-1 seconds.
 */
public final class CompactDuration extends AbstractDuration {
  /**
   * Constructs a duration in terms of its length in hours, minutes, and
   * seconds.
   *
   * @param hours the number of hours
   * @param minutes the number of minutes
   * @param seconds the number of inSeconds
   * @throws IllegalArgumentException if any argument is negative
   */
  public CompactDuration(int hours, int minutes, int seconds) {
    ensureHms(hours, minutes, seconds);
    this.inSeconds = inSeconds(hours, minutes, seconds);
  }

  /**
   * Constructs a duration in terms of its length in seconds.
   *
   * @param inSeconds the number of seconds (non-negative)
   * @throws IllegalArgumentException {@code inSeconds} is negative
   */
  public CompactDuration(long inSeconds) {
    if (inSeconds < 0) {
      throw new IllegalArgumentException("must be non-negative");
    }

    this.inSeconds = inSeconds;
  }

  private final long inSeconds;

  @Override
  protected Duration fromSeconds(long seconds) {
    return new CompactDuration(seconds);
  }

  @Override
  public long inSeconds() {
    return inSeconds;
  }

  @Override
  public String asHms() {
    return String.format("%d:%02d:%02d",
                          hoursOf(inSeconds),
                          minutesOf(inSeconds),
                          secondsOf(inSeconds));
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
    } else if (s == 'h' || s == 'H') {
      return zeroPadding(isUpper, hoursOf(this.inSeconds));
    } else if (s == 'm' || s == 'M') {
      return zeroPadding(isUpper, minutesOf(this.inSeconds));
    } else if (s == 's' || s == 'S') {
      return zeroPadding(isUpper, this.inSeconds % 60);
    } else if (s == '%') {
      return "%";
    } else {
      throw new IllegalArgumentException("Format specifier malformed.");
    }
  }
}
