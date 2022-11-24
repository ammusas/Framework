package Sample;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
@Test
public class Take_ScreenShot {
	WebDriver driver;

	public void ReportDemo() throws IOException {
		
	
		//Create HTML Report Template
		ExtentHtmlReporter reporter=new ExtentHtmlReporter("./Report/ExecutionReport.html");
		
		//Attach the report to the template
		ExtentReports reports=new ExtentReports();
		reports.attachReporter(reporter);
		
		//Create a test with test case
		ExtentTest test=reports.createTest("Demo Web Shop Regression");
		
		//Test Steps
		System.setProperty("webdriver.chrome.driver","./Drivers/chromedriver.exe");
		 driver=new ChromeDriver();
		driver.get("https://demowebshop.tricentis.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		test.log(Status.PASS,"Application Launched Successfully");
		
		
		//Capture_Screen_Shot("launch DWS")
		
		test.pass("Application Launch").addScreenCaptureFromPath(Capture_Screen_Shot("Launch DWS"));
		
		
		
		driver.findElement(By.id("small-searchterms")).sendKeys("books");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		test.log(Status.PASS,"Product Search is Successfull");
		Capture_Screen_Shot("Search");
		test.log(Status.INFO,"Search Is Completed");
		driver.close();
		
		reports.flush();
	
}
	//Method to capture screen shot
	public String Capture_Screen_Shot(String stepname) throws IOException {
		TakesScreenshot ts=(TakesScreenshot)driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		String destpath="./ScreenShot/"+stepname+".png";
		FileHandler.copy(src,new File(destpath));
		return "."+destpath;
	}
}
