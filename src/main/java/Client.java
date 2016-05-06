import java.util.List;
import org.sql2o.*;

public class Client {
  private int id;
  private String name;
  private int stylist_id;

  public Client(String name, int stylist_id) {
    this.name = name;
    this.stylist_id = stylist_id;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int getStylistId() {
    return stylist_id;
  }

}
