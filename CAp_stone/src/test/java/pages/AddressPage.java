package pages;


import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.util.ArrayList;

public class AddressPage {
    WebDriver driver;
    WebDriverWait wait;

    By loginIcon = By.cssSelector("a.icon-login.cursor-pointer");
    By addressLabel = By.xpath("//p[span[contains(@class, 'icon-location')] and contains(text(),'Address')]");
    By addAddressBtn = By.xpath("//a[contains(@class, 'secondary-button') and normalize-space(text())='Add Address']");
    By firstNameInput = By.name("first_name");
    By lastNameInput = By.name("last_name");
    By emailInput = By.name("email");
    By addressInput = By.name("address[]");
    By countrySelect = By.name("country");
    By stateSelect = By.id("state");
    By cityInput = By.name("city");
    By postcodeInput = By.name("postcode");
    By phoneInput = By.name("phone");
    By saveBtn = By.xpath("//button[contains(text(),'Saves')]");

    public AddressPage(WebDriver driver,WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
    }

    public void openNewCustomerLogin(){
        driver.findElement(loginIcon).click();
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
    }

    public void clickAddress() {
        WebElement label = wait.until(ExpectedConditions.elementToBeClickable(addressLabel));
        ((JavascriptExecutor) driver).executeScript(
            "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);" +
            "var elementTop = arguments[0].getBoundingClientRect().top;" +
            "window.scrollBy(0, elementTop - (viewPortHeight / 2));", label);
        Actions actions = new Actions(driver);
        actions.moveToElement(label).click().perform();
    }


 // Assuming you have a locator like
    

    

    public void clickAddAddress() {
        WebElement addAddress = wait.until(ExpectedConditions.elementToBeClickable(addAddressBtn));

        // Scroll with offset to avoid overlays
        ((JavascriptExecutor) driver).executeScript(
            "var element = arguments[0];" +
            "var headerHeight = 80;" + 
            "var rect = element.getBoundingClientRect();" +
            "window.scrollBy(0, rect.top - headerHeight);", addAddress);

        
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addAddress);
    }



    public void enterFirstName(String fname){
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput));
        input.sendKeys(fname);
    }

    public void enterLastName(String lname){
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameInput));
        input.sendKeys(lname);
    }

    public void enterEmail(String email){
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput));
        input.sendKeys(email);
    }

    public void enterAddress(String address){
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(addressInput));
        input.sendKeys(address);
    }

    public void selectCountry(String country){
        Select select = new Select(wait.until(ExpectedConditions.elementToBeClickable(countrySelect)));
        select.selectByVisibleText(country);
    }

    public void selectState(String state){
        Select select = new Select(wait.until(ExpectedConditions.elementToBeClickable(stateSelect)));
        select.selectByVisibleText(state);
    }

    public void enterCity(String city){
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(cityInput));
        input.sendKeys(city);
    }

    public void enterPostcode(String postcode){
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(postcodeInput));
        input.sendKeys(postcode);
    }

    public void enterPhone(String phone){
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(phoneInput));
        input.sendKeys(phone);
    }

    public void clickSaveBtn(){
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(saveBtn));
        btn.click();
    }
}
