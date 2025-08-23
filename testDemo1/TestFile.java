package Excel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class TestFile {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test(dataProvider = "getdata", dataProviderClass = ExcelDataProvider.class)
    public void login(String username, String password) {
        driver.findElement(By.id("user-name")).sendKeys(username.trim());
        driver.findElement(By.id("password")).sendKeys(password.trim());
        driver.findElement(By.id("login-button")).click();

        if (username.trim().equals("standard_user") && password.trim().equals("secret_sauce")) {
            // ✅ Expected valid login
            Assert.assertTrue(driver.getCurrentUrl().contains("inventory"),
                    "Login failed for valid user: " + username);
        } else {
            // ❌ Fail the test for invalid credentials
            Assert.fail("Invalid login should fail for user: " + username);
        }
    }
}

