package Day27;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;

public class SauceDemoWithLogger {

    WebDriver driver;
    WebDriverWait wait;
    Logger logger = LogManager.getLogger(SauceDemoWithLogger.class);

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        logger.info("Browser launched successfully.");
    }

    @Test
    public void loginAndCartTest() {
        try {
            driver.get("https://www.saucedemo.com/");
            logger.info("Opened SauceDemo website.");

            WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
            username.sendKeys("standard_user");
            logger.info("Entered username.");

            WebElement password = driver.findElement(By.id("password"));
            password.sendKeys("secret_sauce");
            logger.info("Entered password.");

            driver.findElement(By.id("login-button")).click();
            logger.info("Clicked on login button.");

            // Synchronization: Wait until product page loads
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_list")));
            logger.info("Login successful, product list visible.");

            // Add to cart
            driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
            logger.info("Added backpack to cart.");

            driver.findElement(By.className("shopping_cart_link")).click();
            logger.info("Navigated to cart page.");

            String itemName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_item_name"))).getText();
            logger.info("Item in cart: " + itemName);

        } catch (TimeoutException e) {
            logger.error("Element not found within timeout!", e);
        } catch (NoSuchElementException e) {
            logger.error("Element not present on page!", e);
        } catch (Exception e) {
            logger.error("Unexpected error occurred!", e);
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            logger.info("Browser closed successfully.");
        }
    }
}
