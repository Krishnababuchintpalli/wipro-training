package Day27;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DragAndDropTest {
    WebDriver driver = new ChromeDriver();  // ✅ Added initialization
     @Test 
    public void handleDragAndDropTest() {
        driver.get("https://jqueryui.com/droppable/");
        driver.switchTo().frame(driver.findElement(By.className("demo-frame")));

        WebElement source = driver.findElement(By.id("draggable"));
        WebElement target = driver.findElement(By.id("droppable"));

        Actions actions = new Actions(driver);
        actions.dragAndDrop(source, target).perform();

        Assert.assertEquals(target.getText(), "Dropped!");


    }
}