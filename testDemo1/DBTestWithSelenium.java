package Day27;

import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.sql.*;
import java.time.Duration;

public class DBTestWithSelenium {
    JdbcUtil jdbcUtil = new JdbcUtil();
    ResultSet resultSet;
    WebDriver driver;

    @BeforeClass
    public void setup() throws ClassNotFoundException, SQLException {
        // DB details
        String dbUrl = "jdbc:mysql://localhost:3306/Jdbc";
        String user = "root";
        String pass = "Hima@1304";

        // Connect DB + Launch Browser
        jdbcUtil.connect(dbUrl, user, pass);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void readDataAndTest() throws SQLException {
        String query = "SELECT * FROM users WHERE status='active'";
        resultSet = jdbcUtil.executeQuery(query);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        while (resultSet.next()) {
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");

            // Navigate to SauceDemo
            driver.get("https://www.saucedemo.com/");

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
            driver.findElement(By.id("user-name")).clear();
            driver.findElement(By.id("user-name")).sendKeys(username);

            driver.findElement(By.id("password")).clear();
            driver.findElement(By.id("password")).sendKeys(password);

            driver.findElement(By.id("login-button")).click();

            boolean loginPassed = false;
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_list")));
                System.out.println("Login success for: " + username);
                loginPassed = true;
            } catch (TimeoutException e) {
                System.out.println(" Login failed for: " + username);
            }

            // Insert result into test_results table
            String insertQuery = "INSERT INTO test_results (username, result) VALUES (?, ?)";
            PreparedStatement pstmt = jdbcUtil.getConnection().prepareStatement(insertQuery);
            pstmt.setString(1, username);
            pstmt.setString(2, loginPassed ? "PASS" : "FAIL");
            pstmt.executeUpdate();
        }
    }

    @AfterClass
    public void tearDown() throws SQLException {
        if (driver != null) driver.quit();
        jdbcUtil.close();
    }
}
