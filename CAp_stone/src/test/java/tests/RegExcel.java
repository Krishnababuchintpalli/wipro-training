package tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;
import utils.ExcelUtils;

import java.time.Duration;

public class RegExcel {

    WebDriver driver;
    WebDriverWait wait;

    String excelPath = "C:\\Users\\deept\\OneDrive\\Desktop\\velocity.xlsx";

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.get("https://demo.bagisto.com/bagisto-common/");
    }

    // ---------------- DATA PROVIDERS ----------------
    @DataProvider(name = "signupData")
    public Object[][] getSignupData() {
        return ExcelUtils.getSheetData(excelPath, "Sheet1");
    }

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        return ExcelUtils.getSheetData(excelPath, "Sheet2");
    }

    // ---------------- SIGNUP TEST ----------------
    @Test(dataProvider = "signupData", priority = 1)
    public void signupTest(String firstName, String lastName, String emailInput, String passwordInput, String confirmPassword) {
        try {
            clickElement(By.cssSelector("span.icon-users"));
            clickElement(By.xpath("//a[contains(text(),'Sign Up')]"));

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("first_name")));
            driver.findElement(By.name("first_name")).sendKeys(firstName);
            driver.findElement(By.name("last_name")).sendKeys(lastName);
            driver.findElement(By.name("email")).sendKeys(emailInput);
            driver.findElement(By.name("password")).sendKeys(passwordInput);
            driver.findElement(By.name("password_confirmation")).sendKeys(confirmPassword);

            jsClick(driver.findElement(By.id("agreement")));
            clickElement(By.cssSelector("button[type='submit'].primary-button"));

            sleep(3000);
            System.out.println("✅ User registered: " + emailInput);

        } catch (Exception e) {
            System.out.println("❌ Signup failed for: " + emailInput);
            e.printStackTrace();
        }
    }

  
    private void clickElement(By by) {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(by));
        el.click();
        sleep(1000);
    }

    private void jsClick(WebElement el) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
        sleep(500);
    }

    private void sleep(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException ignored) {}
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}