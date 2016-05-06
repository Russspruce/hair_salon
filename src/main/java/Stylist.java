import java.util.List;
import org.sql2o.*;

public class Stylist {
  private int id;
  private String styler;

  public Stylist(String styler) {
    this.styler = styler;
  }

  public String getStyler() {
    return styler;
  }

  public int getId() {
    return id;
  }

}
