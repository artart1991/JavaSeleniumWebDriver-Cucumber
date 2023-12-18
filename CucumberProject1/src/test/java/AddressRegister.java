import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;


public class AddressRegister {
    private WebDriver driver;

    @Given("User on mystore-testlab.coderslab.pl not logged in")
    public void userOnMainPageNotLoggedIn() {
        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        driver.get("https://mystore-testlab.coderslab.pl");
    }
    @When("^User go to sign in page")
    public void userGoesToSignInPage() {
        driver.findElement(By.cssSelector("#_desktop_user_info > div > a > span")).click();
    }

    @And("^Enter email (.*) and enter password (.*) and submit")
    public void userEnterEmailAndPasswordAndSubmit(String email, String password) {
        driver.findElement(By.id("field-email")).sendKeys(email);
        driver.findElement(By.id("field-password")).sendKeys(password);
        driver.findElement(By.id("submit-login")).click();
    }

    @And("^User go to add first address page")
    public void userGoesToAddAddressPage() {
        driver.findElement(By.id("addresses-link")).click();
        driver.findElement(By.cssSelector("#content > div.addresses-footer > a > span")).click();
    }

    @And("^fulfill registration data with alias (.*) and address (.*) and city (.*) and postal_code (.*) and phone (.*) submit")
    public void fulfillRegistrationForm(String alias, String address, String city, String postal_code, String phone) {
        driver.findElement(By.cssSelector("#field-alias")).sendKeys(alias);
        driver.findElement(By.cssSelector("#field-address1")).sendKeys(address);
        driver.findElement(By.id("field-city")).sendKeys(city);
        driver.findElement(By.cssSelector("#field-postcode")).sendKeys(postal_code);
        driver.findElement(By.cssSelector("#field-phone")).sendKeys(phone);
        driver.findElement(By.cssSelector("#content > div > div > form > footer > button")).click();
    }
    @Then("^Address successfully added")
    public void addressSuccessfullyAdded() {
        Assert.assertEquals("Address successfully added!",
                driver.findElement(By.xpath("//*[@id=\"notifications\"]/div/article/ul/li")).getText().trim());
    }

    @Then("^User check data correctnes alias (.*) and address (.*) and city (.*) and postal_code (.*) and phone (.*)")
   public boolean containsAddress(String alias, String address, String city, String postal_code, String phone) {
        List<WebElement> addresses = driver.findElements(By.className("address-body"));
        if(!addresses.isEmpty()) {
            for (WebElement searchedAddress : addresses) {
                String addressAlias = searchedAddress.findElement(By.tagName("h4")).getText();
               if (addressAlias.equals(alias)) {
                   WebElement addressBody = searchedAddress.findElement(By.tagName("address"));
                    if(addressBody != null) {
                        String addressBodyText = addressBody.getText();
                        return addressBodyText.contains(address) && addressBodyText.contains(city) && addressBodyText.contains(postal_code) && addressBodyText.contains(phone);
                    }
                }
            }
        }
        return false;
    }
    @And("^User delete address")
    public void userDeleteAddress() {
        driver.findElement(By.xpath("/html/body/main/section/div/div/section/section/div[2]/article/div[2]/a[2]")).click();
    }

    @Then("Address successfully delete")
    public void addressSuccessfullyDeleted() {
        Assert.assertEquals("Address successfully deleted!",
                driver.findElement(By.xpath("//*[@id=\"notifications\"]/div/article/ul/li")).getText().trim());
    }

}
