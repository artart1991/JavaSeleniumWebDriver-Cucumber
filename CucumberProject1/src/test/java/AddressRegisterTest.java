import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/CucumberProject1/Features/address-register.feature", plugin = {"pretty","html:out.html"})

public class AddressRegisterTest {
}
