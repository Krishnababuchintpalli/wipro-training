package stepDefinitions;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import io.cucumber.java.en.*;
import org.testng.Assert;
import java.time.Duration;

public class AdminLoginSteps {

    WebDriver driver;
    WebDriverWait wait;

    @Given("User launches Chrome browser")
    public void user_launches_chrome_browser() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @And("User maximizes the browser window")
    public void user_maximizes_the_browser_window() {
        driver.manage().window().maximize();
    }

    @And("User deletes all cookies")
    public void user_deletes_all_cookies() {
        driver.manage().deleteAllCookies();
    }

    @And("User navigates to the Bagisto admin login page")
    public void user_navigates_to_the_bagisto_admin_login_page() {
        driver.get("https://demo.bagisto.com/mobikul-common/admin/login");
    }

    @And("User waits for login page to load")
    public void user_waits_for_login_page_to_load() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
    }

    @When("User clears email and enters admin email as {string}")
    public void user_clears_email_and_enters_admin_email_as(String email) {
        WebElement emailField = driver.findElement(By.name("email"));
        emailField.clear();
        emailField.sendKeys(email);
    }

    @And("User clears password and enters admin password as {string}")
    public void user_clears_password_and_enters_admin_password_as(String password) {
        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    @And("User clicks on the login button")
    public void user_clicks_on_the_login_button() {
        driver.findElement(By.xpath("//button[contains(text(),'Sign In')]")).click();
    }

    @Then("User should be redirected to admin dashboard")
    public void user_should_be_redirected_to_admin_dashboard() {
        wait.until(ExpectedConditions.urlContains("/dashboard"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/dashboard"), "Not redirected to dashboard");
    }

    // ---------- Customers Flow ----------
    @When("User clicks on Customers in the sidebar")
    public void user_clicks_on_customers_in_the_sidebar() {
        WebElement customersMenu = wait.until(
            ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(text(),'Customers')]"))
        );
        customersMenu.click();
    }

    @Then("User should be redirected to the Customers page")
    public void user_should_be_redirected_to_the_customers_page() {
        wait.until(ExpectedConditions.urlContains("/customers"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/customers"));
    }

    @When("User clicks on Create Customer button")
    public void user_clicks_on_create_customer_button() {
        WebElement createButton = wait.until(
            ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Create Customer')]"))
        );
        createButton.click();
    }

    @And("User enters customer details")
    public void user_enters_customer_details() {
        // Fill First Name
        WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first_name")));
        firstName.sendKeys("Naveen");

        // Fill Last Name
        WebElement lastName = driver.findElement(By.id("last_name"));
        lastName.sendKeys("Ravalavalavasa");

        // Fill Email
        WebElement email = driver.findElement(By.id("email"));
        email.sendKeys("Deemo@gmail.com");

        // Fill Contact Number
        WebElement phone = driver.findElement(By.id("phone"));
        phone.sendKeys("5689756892");

        // Fill DOB
        WebElement dob = driver.findElement(By.id("dob"));
        dob.sendKeys("01/01/2002");

        // Select Gender
        WebElement genderDropdown = driver.findElement(By.id("gender"));
        Select gender = new Select(genderDropdown);
        gender.selectByVisibleText("Male");

        // Select Customer Group
        WebElement groupDropdown = driver.findElement(By.id("customerGroup"));
        Select group = new Select(groupDropdown);
        group.selectByVisibleText("General");
    }

    @And("User clicks on Save Customer button")
    public void user_clicks_on_save_customer_button() {
        WebElement saveBtn = driver.findElement(By.xpath("//button[contains(@class,'primary-button') and contains(text(),'Save')]"));
        saveBtn.click();
    }

    @Then("New customer should be created successfully")
    public void new_customer_should_be_created_successfully() {
        WebElement successMsg = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Customer created successfully')]"))
        );
        Assert.assertTrue(successMsg.isDisplayed(), "Customer not created successfully!");
    }

    // ---------- Logout Flow ----------
    @And("User clicks on profile button")
    public void user_clicks_on_profile_button() {
        WebElement profileBtn = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@class,'rounded-full') and contains(text(),'E')]"))
        );
        // Use JS click to avoid ElementClickInterceptedException
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", profileBtn);
    }

    @And("User clicks on logout link")
    public void user_clicks_on_logout_link() {
        WebElement logoutLink = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Logout')]"))
        );
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", logoutLink);
    }

    @Then("User should be logged out successfully")
    public void user_should_be_logged_out_successfully() {
        wait.until(ExpectedConditions.urlContains("/login"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/login"), "Logout not successful");
    }

    @And("User closes the browser")
    public void user_closes_the_browser() {
        driver.quit();
    }
}