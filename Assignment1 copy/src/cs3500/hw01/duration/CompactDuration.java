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

  @Override
  public String format(String template) {

    StringBuilder formattedString = new StringBuilder();

    for (int i = 0; i < template.length(); i++) {
      if (template.charAt(i) == '%') {
        //TODO: helper method taking i and i+1 to do string formatting
        formattedString.append(formatHelper(this, template.charAt(i + 1)));
        i++;
      }
      else {
        formattedString.append(template.charAt(i));
      }

    }
    return formattedString.toString();
  }

  private String formatHelper(Duration d, char s) {
    if (s == 't') {
      return Long.toString(this.inSeconds());
    } else if (s == 'h') {
      return Long.toString(hoursOf(this.inSeconds));
    } else if (s == 'H') {
      return zeroPadding(hoursOf(this.inSeconds));
    } else if (s == 'm') {
      return Long.toString(minutesOf(this.inSeconds));
    } else if (s == 'M') {
      return zeroPadding(minutesOf(this.inSeconds));
    } else if (s == 's') {
      return Long.toString(this.inSeconds % 60);
    } else if (s == 'S') {
      return zeroPadding(this.inSeconds % 60);
    } else if (s == '%') {
      return "%";
    } else {
      throw new IllegalArgumentException("Format specifier not recognized.");
    }
  }

  private String zeroPadding(long num) {
    if (num < 10) {
      return "0" + Long.toString(num);
    }
    else {
      return Long.toString(num);
    }
  }
}
