package pages;



import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.Test;

import java.time.LocalDate;

public class DashboardPage {
    WebDriver driver;
    WebDriverWait wait;

    By darkModeToggle = By.cssSelector("span.cursor-pointer.icon");
    By calendarInput = By.xpath("//input[@placeholder='Start Date']");
    By notifIcon = By.cssSelector("span.icon-notification[title='Notifications']");
    By viewAllNotif = By.xpath("//a[contains(text(),'View All')]");
    By searchInput = By.xpath("//input[@placeholder='Mega Search']");
    By customersDiv = By.xpath("//div[contains(@class, 'cursor-pointer') and contains(text(),'Customers')]");
    By firstSearchResult = By.xpath("//div[contains(@class,'grid') and contains(@class,'overflow-auto')]//a[1]");

    public DashboardPage(WebDriver driver,WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
    }
    

    public void enableDarkMode() {
        try {
            By darkModeToggle = By.cssSelector("span.icon-dark"); // update accordingly
            WebElement toggle = wait.until(ExpectedConditions.elementToBeClickable(darkModeToggle));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", toggle);
            toggle.click();
            System.out.println("Dark mode toggled.");
        } catch (TimeoutException e) {
            System.err.println("Dark mode toggle not found or not clickable.");
            throw e; // rethrow to fail test
        }
    }



    public void openCalendar() {
        WebElement cal = wait.until(ExpectedConditions.visibilityOfElementLocated(calendarInput));
        // Scroll element just below the sticky header (~60px offset)
        ((JavascriptExecutor) driver).executeScript(
            "var element = arguments[0];" +
            "var headerOffset = document.querySelector('header.sticky')?.offsetHeight || 0;" +
            "var elementPosition = element.getBoundingClientRect().top + window.scrollY;" +
            "window.scrollTo({ top: elementPosition - headerOffset - 10, behavior: 'smooth' });",
            cal);
        // Use JS click to avoid interception
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cal);
    }

    public void selectToday() {
        String day = String.valueOf(LocalDate.now().getDayOfMonth());
        By dayLocator = By.xpath("//div[contains(@class,'flatpickr-calendar')]//span[text()='" + day + "']");
        WebElement dayElement = wait.until(ExpectedConditions.elementToBeClickable(dayLocator));
        // Make sure calendar is fully visible before clicking
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dayElement);
        dayElement.click();
    }


    

    public void openNotifications(){
        WebElement icon = wait.until(ExpectedConditions.elementToBeClickable(notifIcon));
        icon.click();
    }

    public void clickViewAllNotifications(){
        WebElement all = wait.until(ExpectedConditions.elementToBeClickable(viewAllNotif));
        all.click();
    }

    public void searchCustomer(String name) throws InterruptedException {
        WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput));
        search.clear();
        search.sendKeys(name);
        Thread.sleep(1500);
    }

    
    

 // Locator for first customer search result
 
 public void clickCustomersDiv() {
     WebElement customers = wait.until(ExpectedConditions.elementToBeClickable(customersDiv));
     ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", customers);
     ((JavascriptExecutor) driver).executeScript("arguments[0].click();", customers);
 }
   


   
    public void waitForSearchResults(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstSearchResult));
    }
}
