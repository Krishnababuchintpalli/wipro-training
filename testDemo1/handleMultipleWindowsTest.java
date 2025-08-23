package Day27;

import java.util.Iterator;
import java.util.Set;
	import java.util.Iterator;
	import java.util.Set;
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

	public class handleMultipleWindowsTest {
	    WebDriver driver = new ChromeDriver(); // ✅ declare & initialize driver
       @Test
	    public void handleMultipleWindowsTest() {

	        driver.get("https://the-internet.herokuapp.com/windows");

	        // Click to open new tab
	        driver.findElement(By.linkText("Click Here")).click();

	        // Get all window handles
	        Set<String> windows = driver.getWindowHandles();
	        Iterator<String> it = windows.iterator();

	        String parentWindow = it.next();
	        String childWindow = it.next();

	        // Switch to child window
	        driver.switchTo().window(childWindow);
	        System.out.println("Child Window Title: " + driver.getTitle());

	        // Close child and switch back
	        driver.close();
	        driver.switchTo().window(parentWindow);
	    }
	}