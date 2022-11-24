package Sample;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.internal.annotations.ITest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

@Test
public class Working_With_Extent_Report {
	public void ReportDemo() {
		
		
		//Create HTML Report Template
		ExtentHtmlReporter reporter=new ExtentHtmlReporter("./Report/ExecutionReport.html");
		
		//Attach the report to the template
		ExtentReports reports=new ExtentReports();
		reports.attachReporter(reporter);
		
		//Create a test with test case
		ExtentTest test=reports.createTest("Demo Web Shop Regression");
		
		//Test Steps
		System.setProperty("webdriver.chrome.driver","./Drivers/chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://demowebshop.tricentis.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		test.log(Status.PASS,"Application Launched Successfully");
		driver.findElement(By.id("small-searchterms")).sendKeys("books");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		test.log(Status.PASS,"Product Search is Successfull");
		test.log(Status.INFO,"Search Is Completed");
		driver.close();
		
		reports.flush();
	
		
		
		
	}

}
