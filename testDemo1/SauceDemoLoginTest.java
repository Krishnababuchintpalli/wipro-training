package Day27;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;
import java.time.Duration;

public class SauceDemoLoginTest {

    private static final Logger logger = LogManager.getLogger(SauceDemoLoginTest.class);

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        logger.info("Launching the browser and navigating to SauceDemo site");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com/");
    }

    @Test
    public void testLogin() {
        try {
            logger.info("Entering username");
            WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
            username.sendKeys("standard_user");

            logger.info("Entering password");
            WebElement password = driver.findElement(By.id("password"));
            password.sendKeys("secret_sauce");

            logger.info("Clicking Login button");
            WebElement loginBtn = driver.findElement(By.id("login-button"));
            loginBtn.click();

            logger.info("Checking if Products page is displayed");
            WebElement productsTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("title")));
            Assert.assertTrue(productsTitle.isDisplayed(), "Login failed!");
            System.out.println("✅ Login successful, Products page found!");
            logger.info("Login successful!");

        } catch (NoSuchElementException e) {
            logger.error("Element not found: " + e.getMessage());
            Assert.fail("Test failed due to missing element.");
        } catch (TimeoutException e) {
            logger.error("Timeout while waiting: " + e.getMessage());
            Assert.fail("Test failed due to timeout.");
        } catch (Exception e) {
            logger.error("Unexpected error: " + e.getMessage());
            Assert.fail("Test failed due to unexpected exception.");
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            logger.info("Closing the browser");
            driver.quit();
        }
    }
}
