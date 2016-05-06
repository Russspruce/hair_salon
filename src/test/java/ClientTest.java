import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class ClientTest {

  @Rule
public DatabaseRule database = new DatabaseRule();


  @Test
  public void Client_instantiatesCorrectly_true() {
    Client testClient = new Client("Mindy", 2);
    assertEquals(true, testClient instanceof Client);
  }

  @Test
  public void getName_stylistInstantiatesWithName_String() {
    Client testClient = new Client("Mindy", 2);
    assertEquals("Mindy", testClient.getName());
  }

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Client.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueifNameIsTheSame() {
  Client firstClient = new Client("Cate", 2);
  Client secondClient = new Client("Cate", 2);
  assertTrue(firstClient.equals(secondClient));
  }

// GET ID TEST
  @Test
public void save_assignsIdToObject() {
  Client testClient = new Client("Richard", 3);
  testClient.save();
  Client savedClient = Client.all().get(0);
  assertEquals(testClient.getId(), savedClient.getId());
  }

@Test
public void find_findsClientInDatabase_true() {
  Client testClient = new Client("Susie", 1);
  testClient.save();
  Client savedClient = Client.find(testClient.getId());
  assertTrue(testClient.getId() == savedClient.getId());
  }

}
