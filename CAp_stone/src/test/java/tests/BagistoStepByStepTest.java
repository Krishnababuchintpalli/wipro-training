package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.*;

public class BagistoStepByStepTest extends BaseTest {
    LoginPage loginPage;
    DashboardPage dashboard;
    CustomersPage customers;
    AddressPage address;

    @Test(priority = 1)
    public void openAdminPanel() {
        loginPage = new LoginPage(driver, wait);
        dashboard = new DashboardPage(driver, wait);
        customers = new CustomersPage(driver, wait);
        address = new AddressPage(driver, wait);
        loginPage.open("https://demo.bagisto.com/bagisto-common/admin/login");
    }

    @Test(priority = 2, dependsOnMethods = "openAdminPanel")
    public void enterEmail() {
        loginPage.enterEmail("admin@example.com");
    }

    @Test(priority = 3, dependsOnMethods = "enterEmail")
    public void enterPassword() {
        loginPage.enterPassword("admin123");
    }

    @Test(priority = 4, dependsOnMethods = "enterPassword")
    public void clickSignIn() {
        loginPage.clickSignIn();
    }

    @Test(priority = 5, dependsOnMethods = "clickSignIn")
    public void enableDarkMode() {
        dashboard.enableDarkMode();
    }

    @Test(priority = 6, dependsOnMethods = "enableDarkMode")
    public void openCalendar() {
        dashboard.openCalendar();
    }

    @Test(priority = 7, dependsOnMethods = "openCalendar")
    public void selectToday() {
        dashboard.selectToday();
    }

    @Test(priority = 8, dependsOnMethods = "selectToday")
    public void openNotifications() {
        dashboard.openNotifications();
    }

    @Test(priority = 9, dependsOnMethods = "openNotifications")
    public void clickViewAllNotifications() {
        dashboard.clickViewAllNotifications();
    }

    @Test(priority = 10, dependsOnMethods = "clickViewAllNotifications")
    public void searchCustomer() throws InterruptedException {
        dashboard.searchCustomer("rocky");
        Thread.sleep(1500);
    }

    @Test(priority = 11, dependsOnMethods = "searchCustomer")
    public void clickCustomersDiv() {
        dashboard.clickCustomersDiv();
    }

    @Test(priority = 12, dependsOnMethods = "clickCustomersDiv")
    public void waitForSearchResults() {
        dashboard.waitForSearchResults();
    }

    @Test(priority = 13, dependsOnMethods = "waitForSearchResults")
    public void clickFirstSearchResult() {
        customers.clickFirstSearchResult();
    }

    @Test(priority = 14, dependsOnMethods = "clickFirstSearchResult")
    public void waitForDeleteIcon() {
        customers.waitForDeleteIcon();
    }

    @Test(priority = 15, dependsOnMethods = "waitForDeleteIcon")
    public void clickDeleteIcon() {
        customers.clickDeleteIcon();
    }

    @Test(priority = 16, dependsOnMethods = "clickDeleteIcon")
    public void waitForAgreeBtn() {
        customers.waitForAgreeBtn();
    }

    @Test(priority = 17, dependsOnMethods = "waitForAgreeBtn")
    public void clickAgreeBtn() {
        customers.clickAgreeBtn();
    }

    @Test(priority = 18, dependsOnMethods = "clickAgreeBtn")
    public void goToCustomersMenu() {
        customers.goToCustomersMenu();
    }

    @Test(priority = 19, dependsOnMethods = "goToCustomersMenu")
    public void clickCustomersSubMenu() {
        customers.clickCustomersSubMenu();
    }

    @Test(priority = 20, dependsOnMethods = "clickCustomersSubMenu")
    public void clickCreateCustomerBtn() {
        customers.clickCreateCustomerBtn();
    }

    @Test(priority = 21, dependsOnMethods = "clickCreateCustomerBtn")
    public void enterFirstName() {
        customers.enterFirstName("Johny");
    }

    @Test(priority = 22, dependsOnMethods = "enterFirstName")
    public void enterLastName() {
        customers.enterLastName("hDje");
    }

    @Test(priority = 23, dependsOnMethods = "enterLastName")
    public void enterCustomerEmail() {
        customers.enterCustomerEmail("rocky128@test.com");
    }

    @Test(priority = 24, dependsOnMethods = "enterCustomerEmail")
    public void selectGender() {
        customers.selectGender("Female");
    }

    @Test(priority = 25, dependsOnMethods = "selectGender")
    public void clickSaveCustomerBtn() throws InterruptedException {
        customers.clickSaveCustomerBtn();
        Thread.sleep(4000);
    }

    @Test(priority = 26, dependsOnMethods = "clickSaveCustomerBtn")
    public void clickNewCustomerLogin() {
        address.openNewCustomerLogin();
    }

    @Test(priority = 27, dependsOnMethods = "clickNewCustomerLogin")
    public void clickAddress() {
        address.clickAddress();
    }

    @Test(priority = 28, dependsOnMethods = "clickAddress")
    public void clickAddAddressBtn() {
        address.clickAddAddress();
    }

    @Test(priority = 29, dependsOnMethods = "clickAddAddressBtn")
    public void enterAddressFirstName() {
        address.enterFirstName("John");
    }

    @Test(priority = 30, dependsOnMethods = "enterAddressFirstName")
    public void enterAddressLastName() {
        address.enterLastName("Doe");
    }

    @Test(priority = 31, dependsOnMethods = "enterAddressLastName")
    public void enterAddressEmail() {
        address.enterEmail("john.doe@example.com");
    }

    @Test(priority = 32, dependsOnMethods = "enterAddressEmail")
    public void enterStreetAddress() {
        address.enterAddress("123 Main St");
    }

    @Test(priority = 33, dependsOnMethods = "enterStreetAddress")
    public void selectCountry() {
        address.selectCountry("India");
    }

    @Test(priority = 34, dependsOnMethods = "selectCountry")
    public void selectState() {
        address.selectState("Andhra Pradesh");
    }

    @Test(priority = 35, dependsOnMethods = "selectState")
    public void enterCity() {
        address.enterCity("Hyderabad");
    }

    @Test(priority = 36, dependsOnMethods = "enterCity")
    public void enterPostCode() {
        address.enterPostcode("500001");
    }

    @Test(priority = 37, dependsOnMethods = "enterPostCode")
    public void enterPhone() {
        address.enterPhone("9876543210");
    }

    @Test(priority = 38, dependsOnMethods = "enterPhone")
    public void clickSaveAddressBtn() {
        address.clickSaveBtn();
    }

    private void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {}
    }
}