import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class StylistTest {

  @Before
 public void setUp() {
   DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/restaurant_review_test", null, null);
}
  @After
 public void tearDown() {
   try(Connection con = DB.sql2o.open()) {
      String deleteReviewsQuery = "DELETE FROM review *;";
      String deleteRestaurantsQuery = "DELETE FROM restaurant *;";
      String deleteCuisineQuery = "DELETE FROM cuisine *;";
      con.createQuery(deleteReviewsQuery).executeUpdate();
      con.createQuery(deleteRestaurantsQuery).executeUpdate();
      con.createQuery(deleteCuisineQuery).executeUpdate();
    }
}

public void Stylist_instantiatesCorrectly_true() {
  Stylist testStylist = new Stylist("John Doe");
  assertEquals(true, testStylist instanceof Stylist);
  }
}
