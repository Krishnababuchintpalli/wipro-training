package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class CustomersPage {
    WebDriver driver;
    WebDriverWait wait;
  
    By firstCustomerLink = By.xpath("(//a[contains(@href,'/customers/view')])[1]");

    //By firstSearchResult = By.xpath("//div[contains(@class,'grid') and contains(@class,'overflow-auto')]//a[1]");
    By deleteIcon = By.xpath("//div[contains(@class, 'inline-flex') and contains(text(), 'Delete Account')]");
    By agreeBtn = By.xpath("//button[contains(text(),'Agree')]");
    By customersMenu = By.xpath("//p[contains(text(),'Customers')]");
    By customersSubMenu = By.xpath("//a[contains(text(),'Customers')]");
    By createCustomerBtn = By.xpath("//button[contains(text(),'Create Customer')]");

    By firstNameInput = By.id("first_name");
    By lastNameInput = By.id("last_name");
    By emailInput = By.id("email");
    By genderSelect = By.id("gender");
    By saveCustomerBtn = By.xpath("//button[contains(text(),'Save customer')]");

    public CustomersPage(WebDriver driver,WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
    }

    public void clickFirstSearchResult(){
        WebElement result = wait.until(ExpectedConditions.elementToBeClickable(firstCustomerLink));
        result.click();
    }

    public void waitForDeleteIcon(){
        wait.until(ExpectedConditions.elementToBeClickable(deleteIcon));
    }

    public void clickDeleteIcon() {
        WebElement deleteBtn = wait.until(ExpectedConditions.elementToBeClickable(deleteIcon));
        deleteBtn.click();
        
    }

    public void waitForAgreeBtn(){
        wait.until(ExpectedConditions.elementToBeClickable(agreeBtn));
    }

    public void clickAgreeBtn(){
        driver.findElement(agreeBtn).click();
    }

    public void goToCustomersMenu(){
        WebElement menu = wait.until(ExpectedConditions.elementToBeClickable(customersMenu));
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", menu);
    }

    public void clickCustomersSubMenu(){
        wait.until(ExpectedConditions.elementToBeClickable(customersSubMenu)).click();
    }

    public void clickCreateCustomerBtn(){
        wait.until(ExpectedConditions.elementToBeClickable(createCustomerBtn)).click();
    }

    public void enterFirstName(String fname){
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput));
        input.sendKeys(fname);
    }

    public void enterLastName(String lname){
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameInput));
        input.sendKeys(lname);
    }

    public void enterCustomerEmail(String email){
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput));
        input.sendKeys(email);
    }

    public void selectGender(String gender){
        Select select = new Select(wait.until(ExpectedConditions.elementToBeClickable(genderSelect)));
        select.selectByVisibleText(gender);
    }

    public void clickSaveCustomerBtn(){
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(saveCustomerBtn));
        btn.click();
    }
}
