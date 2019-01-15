package cs3500.hw01.publication;

public class Webpage implements Publication {

  private final String title;
  private final String url;
  private final String retrieved;

  /**
   * Constructs a {@code Book} object.
   *
   * @param title     the title of the webpage
   * @param url       url of the webpage
   * @param retrieved date of retrieval in string format
   */

  public Webpage(String title, String url, String retrieved) {
    this.title = title;
    this.url = url;
    this.retrieved = retrieved;
  }



  @Override
  public String citeApa() {
    return this.title + ". Retrieved " + this.retrieved + ", from " + this.url + ".";
  }

  @Override
  public String citeMla() {
    return "\"" + this.title + ".\" Web. " + this.retrieved + " <" + this.url + ">.";
  }
}

