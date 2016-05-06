import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

public class StylistTest {

  @Rule
public DatabaseRule database = new DatabaseRule();


  @Test
  public void Stylist_instantiatesCorrectly_true() {
    Stylist testStylist = new Stylist("John Doe");
    assertEquals(true, testStylist instanceof Stylist);
  }
  @Test
  public void getStyler_stylistInstantiatesWithStyler_String() {
    Stylist testStylist = new Stylist("John Doe");
    assertEquals("John Doe", testStylist.getStyler());
  }

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Stylist.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfStylersAreTheSame() { //if not the same, will give AssertionError.
    Stylist firstStylist = new Stylist("Joey Mack");
    Stylist secondStylist = new Stylist("Joey Mack");
    assertTrue(firstStylist.equals(secondStylist));
  }

  @Test
  public void save_returnTrueifStylersAreTheSame() {
    Stylist testStylist = new Stylist("Johny B. Good");
    testStylist.save();
    assertTrue(Stylist.all().get(0).equals(testStylist));
  }

  // GET ID TEST
  @Test
  public void save_assignsIdToObject() {
    Stylist myStylist = new Stylist("Jonny Bravo");
    myStylist.save();
    Stylist savedStylist = Stylist.all().get(0);
    assertEquals(myStylist.getId(), savedStylist.getId());
  }

  // FIND RESTAURANT IN DATABASE
  @Test
  public void find_findsStylistInDatabase_true() {
    Stylist myStylist = new Stylist("Leilei Masters");
    myStylist.save();
    Stylist savedStylist = Stylist.find(myStylist.getId());
    assertTrue(myStylist.equals(savedStylist));
  }
}
