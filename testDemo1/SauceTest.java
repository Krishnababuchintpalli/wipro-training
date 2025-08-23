package Excel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

@Listeners({Excel.SauceListener.class, Excel.MyListener_Report.class})
public class SauceTest {

    public WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.out.println("Launching Chrome Browser...");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }

    @Test
    public void loginTest() {
        System.out.println("Executing Login Test...");

        WebElement username = driver.findElement(By.id("user-name"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement loginBtn = driver.findElement(By.id("login-button"));

        username.sendKeys("standard_user");
        password.sendKeys("secret_sauce");
        loginBtn.click();

        String actualTitle = driver.getTitle();
        Assert.assertTrue(actualTitle.contains("Swag"), "Login Failed!");
    }

    @Test
    public void failTest() {
        System.out.println("This test will fail to check Listener...");
        Assert.fail("Intentional Fail");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Browser Closed.");
        }
    }
}
