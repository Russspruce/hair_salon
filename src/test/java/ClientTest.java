import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class ClientTest {


  @Test
  public void Client_instantiatesCorrectly_true() {
    Client testClient = new Client("Mindy", 2);
    assertEquals(true, testClient instanceof Client);
  }

}
