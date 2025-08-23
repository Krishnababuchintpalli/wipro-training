package Excel;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class MyListener_Report implements ITestListener {

    WebDriver driver;
    ExtentSparkReporter spark;
    ExtentReports extent;
    ExtentTest test;

    @Override
    public void onStart(ITestContext context) {
        System.out.println("🚀 Testing starts...");

        // Report Path
        String reportPath = System.getProperty("user.dir") + "/ExtentReport.html";
        spark = new ExtentSparkReporter(reportPath);

        // Attach Reporter
        extent = new ExtentReports();
        extent.attachReporter(spark);

        // Add System Info
        extent.setSystemInfo("Tester", "Deepthi");
        extent.setSystemInfo("Machine", "Windows 10");
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Browser", "Chrome");

        // Report Look & Feel
        spark.config().setDocumentTitle("Sauce Demo Automation Report");
        spark.config().setReportName("Login Test Report");
        spark.config().setTheme(Theme.STANDARD);
        spark.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("🔹 Test Started: " + result.getName());
        test = extent.createTest(result.getName()); // New node in report
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("✅ Test Passed: " + result.getName());
        test.log(Status.PASS, "Test Passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("❌ Test Failed: " + result.getName());
        test.log(Status.FAIL, "Test Failed: " + result.getName());
        test.fail(result.getThrowable());

        // Capture Driver from SauceTest class
        Object testClass = result.getInstance();
        driver = ((SauceTest) testClass).driver;

        // Create Screenshots folder
        File destDir = new File("Screenshots");
        if (!destDir.exists()) destDir.mkdir();

        String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        String fileName = result.getName() + "_" + timeStamp + ".png";

        try {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destFile = new File(destDir, fileName);

            Files.copy(srcFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("📸 Screenshot saved: " + destFile.getPath());

            // Attach Screenshot in Report
            test.addScreenCaptureFromPath(destFile.getAbsolutePath(), result.getName());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("⚠️ Test Skipped: " + result.getName());
        test.log(Status.SKIP, "Test Skipped: " + result.getName());
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        System.out.println("⏰ Test Timeout: " + result.getName());
        test.log(Status.FAIL, "Test failed due to timeout: " + result.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("🏁 Testing Ended...");
        extent.flush(); // Finalize report
    }
}
