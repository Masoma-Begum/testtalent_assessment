package test.com;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Baseclass {

	public static WebDriver driver;
	public static String captchSubFolder;
	public static String captureImage;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	@BeforeTest
	public void browserinformation() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();	

	}

	@AfterMethod
	public void manageCapture(ITestResult testResult) throws IOException {
		if(testResult.getStatus() == ITestResult.FAILURE) {
			System.out.println(testResult.getTestContext().getName()+"_"+testResult.getMethod().getMethodName());
			captureScreen(testResult.getTestContext().getName()+"_"+testResult.getMethod().getMethodName());
		}
	}

		@AfterTest
		public void tiredown() throws Exception {
			Thread.sleep(5000);
			driver.quit();
		}


		public static void captureScreen(String fileName) throws IOException{

			//If image is not existing according to this time an image will be create with time
			if(captureImage == null) {
				LocalTime time = LocalTime.now();
				DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH-mm-ss");
				captureImage = time.format(timeFormatter);	  
			}
			//A folder will be create according to current date
			LocalDate date = LocalDate.now();
			DateTimeFormatter folderFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy/");
			String folder = date.format(folderFormatter);

			File sourceFile =  ((RemoteWebDriver) driver).getScreenshotAs(OutputType.FILE);
			File destFile = new File("./Screenshots/"+folder+captureImage+fileName+".JPG");
			FileUtils.copyFile(sourceFile,destFile);


		}


	}
