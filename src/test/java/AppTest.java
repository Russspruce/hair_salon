import org.sql2o.*;
import org.junit.*;
import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.assertj.core.api.Assertions.assertThat;
import static org.fluentlenium.core.filter.FilterConstructor.*;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Bonjour!");
  }

  @Test
  public void stylistIsCreatedTest() {
    goTo("http://localhost:4567/");
    click("a", withText("Login"));
    fill("#styler").with("Johnny Bravo");
    submit(".btn");
    assertThat(pageSource()).contains("You have been logged in!");
  }

  @Test
  public void stylerIsDisplayedTest() {
    Stylist myStylist = new Stylist("Johnny Bravo");
    myStylist.save();
    String stylistPath = String.format("http://localhost:4567/stylists/%d", myStylist.getId());
    goTo(stylistPath);
    assertThat(pageSource()).contains("Johnny Bravo");
  }

  @Test
  public void confirmToddOnMainPage() {
    goTo("http://localhost:4567/");
    click("a", withText("Login"));
    fill("#styler").with("Sweeny Todd");
    submit(".btn");
    goTo("http://localhost:4567/stylists");
    assertThat(pageSource()).contains("Sweeny Todd");
  }

  @Test
  public void allClientsDisplayNamesonStylistsList() {
    Stylist myStylist = new Stylist("Zohan Bravo!");
    myStylist.save();
    Client firstClient = new Client("Jenny Smith", myStylist.getId());
    firstClient.save();
    Client secondClient = new Client("Timmy Turner", myStylist.getId());
    secondClient.save();
    String stylistPath = String.format("http://localhost:4567/stylists/%d", myStylist.getId());
    goTo(stylistPath);
    assertThat(pageSource()).contains("Jenny Smith");
    assertThat(pageSource()).contains("Timmy Turner");
  }
}
