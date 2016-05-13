import java.util.List;
import org.sql2o.*;
import java.util.Arrays;

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

  public static List<Stylist> all() {
    String sql = "SELECT id, styler FROM stylists";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Stylist.class);
    }
  }

  @Override
  public boolean equals(Object otherStylist) {
    if(!(otherStylist instanceof Stylist)) {
      return false;
    } else {
      Stylist newStylist = (Stylist) otherStylist;
      return this.getStyler().equals(newStylist.getStyler()) &&
        this.getId() == newStylist.getId();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO stylists(styler) VALUES (:styler)";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("styler", this.styler)
      .executeUpdate()
      .getKey();
    }
  }

  public static Stylist find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM stylists where id=:id";
      Stylist stylist = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Stylist.class);
    return stylist;
    }
  }

  public Client findClient(int clientId){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM clients WHERE stylist_id = :stylist_id AND id = :id";
      return con.createQuery(sql)
      .addParameter("stylist_id", this.id)
      .addParameter("id", clientId).executeAndFetchFirst(Client.class);
    }
  }

  public List<Client> getClients() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients WHERE stylist_id=:id";
      return con.createQuery(sql)
      .addParameter("id", this.id)
      .executeAndFetch(Client.class);
    }

  }

}
