package minhajacademy.ExtentReports;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MyExtentReports {

	ExtentReports extent;
	WebDriver driver;
	String path = System.getProperty("user.dir") + "//report//index.html";

	@BeforeTest
	public void config() {
		
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setDocumentTitle("Test Results");
		reporter.config().setReportName("Automation test results");

		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Minhaj");
	}

	@BeforeSuite
	public void webdriverSetup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	@Test
	public void initialRun() throws InterruptedException {
		
		ExtentTest test = extent.createTest("First Extent Report");
		driver.get("https://www.google.com");
		Thread.sleep(2000);
		System.out.println(path);
		System.out.println(driver.getTitle());
		test.addScreenCaptureFromBase64String(path+ "//report//screenshot.png");
		
	}

	@AfterSuite
	public void tearDown()
	{
		driver.close();
		extent.flush();
	}
}
