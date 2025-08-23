package Day27;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.Set;

public class SauceDemoScenarios {

    WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test(priority = 1)
    public void sauceLoginAndCart() {
        driver.get("https://www.saucedemo.com/");

        // Login
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        // Add to cart
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

        // Open cart
        driver.findElement(By.className("shopping_cart_link")).click();

        // Validate
        String itemName = driver.findElement(By.className("inventory_item_name")).getText();
        System.out.println("Item in cart: " + itemName);
    }

    @Test(priority = 2)
    public void handleAlert() {
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");

        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();

        Alert alert = driver.switchTo().alert();
        System.out.println("Alert Text: " + alert.getText());
        alert.accept();
    }

    @Test(priority = 3)
    public void handleMultipleWindows() {
        driver.get("https://the-internet.herokuapp.com/windows");

        String parent = driver.getWindowHandle();
        driver.findElement(By.linkText("Click Here")).click();

        Set<String> windows = driver.getWindowHandles();
        for (String w : windows) {
            if (!w.equals(parent)) {
                driver.switchTo().window(w);
                System.out.println("Child Window Title: " + driver.getTitle());
                driver.close();
            }
        }
        driver.switchTo().window(parent);
    }

    @Test(priority = 4)
    public void handleDragAndDrop() {
        driver.get("https://jqueryui.com/droppable/");

        driver.switchTo().frame(driver.findElement(By.className("demo-frame")));

        WebElement source = driver.findElement(By.id("draggable"));
        WebElement target = driver.findElement(By.id("droppable"));

        Actions actions = new Actions(driver);
        actions.dragAndDrop(source, target).perform();

        driver.switchTo().defaultContent();
    }

    @Test(priority = 5)
    public void handleIframe() {
        driver.get("https://the-internet.herokuapp.com/iframe");

        driver.switchTo().frame("mce_0_ifr");
        WebElement textBox = driver.findElement(By.id("tinymce"));
        textBox.clear();
        textBox.sendKeys("Inside iframe example!");
        driver.switchTo().defaultContent();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
