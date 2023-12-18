import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class FirstOrder {

    private WebDriver driver;

    @Given("^User on mystore-testlab.coderslab.pl not logged in")
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

    @And("^User go to clothes page")
    public void userGoesToProductsPage() {
        driver.findElement(By.cssSelector("#category-3 > a")).click();
    }

    @And("^Find needed product and submit")
    public void submitNeededProduct() {
        driver.findElement(By.cssSelector("#js-product-list > div.products.row > div:nth-child(2) > article > div > div.thumbnail-top > a > img")).click();
    }

    @Then("^Checking if a discount is available")
    public void addressSuccessfullyAdded() {
        Assert.assertEquals("SAVE 20%",
                driver.findElement(By.xpath("//*[@id=\"main\"]/div[1]/div[2]/div[1]/div[2]/div/span[2]")).getText().trim());
    }

    @And("^Select size")
    public void selectSize() {
        driver.findElement(By.cssSelector("#group_1 > option:nth-child(2)")).click();
    }

    @And("^Select quantity (.*)")
    public void selectQuantity(String quantity) {
        WebElement element = driver.findElement(By.name("qty"));
        element.sendKeys(Keys.CONTROL+"a");
        element.sendKeys(quantity);
    }

    @And("^Add products to the basket")
    public void addToTheBasket() {
        driver.findElement(By.xpath("//*[@id=\"add-to-cart-or-refresh\"]/div[2]/div/div[2]/button")).click();
    }

    @And("^User go the checkout page")
    public void goToCheckoutPage() {
        driver.findElement(By.cssSelector("#blockcart-modal > div > div > div.modal-body > div > div.col-md-7 > div > div > a")).click();
        driver.findElement(By.cssSelector("#main > div > div.cart-grid-right.col-xs-12.col-lg-4 > div.card.cart-summary > div.checkout.cart-detailed-actions.js-cart-detailed-actions.card-block > div > a\n")).click();
    }

    @And("^Confirm address")
    public void confirmAddress() {
        driver.findElement(By.cssSelector("#checkout-addresses-step > div > div > form > div.clearfix > button")).click();
    }

    @And("^Select delivery method")
    public void confirmDeliveryMethod() {
        driver.findElement(By.name("delivery_option[7680]"));
        driver.findElement(By.xpath("//*[@id=\"js-delivery\"]/button")).click();
    }

    @And("^Select payment method and submit")
    public void confirmPaymentMethod() {
        driver.findElement(By.cssSelector("#payment-option-1")).click();
        driver.findElement(By.cssSelector("#conditions_to_approve\\[terms-and-conditions\\]")).click();
        driver.findElement(By.cssSelector("#payment-confirmation > div.ps-shown-by-js > button")).click();
    }

    @Then("^Order successfully added")
    public void productsSuccessfullyAdded() {
        Assert.assertEquals("Your order on mystore-testlab.coderslab.pl is complete.",
                driver.findElement(By.cssSelector("#content-hook_payment_return > div > div > div > p:nth-child(1)")).getText().trim());
    }

    @And("^Make a screenshot of order")
    public void takeScreenShot() throws IOException {
        File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src, new File("C:\\Users\\pinea\\OneDrive\\Pulpit\\screens\\screenshot.png"));
    }


}
