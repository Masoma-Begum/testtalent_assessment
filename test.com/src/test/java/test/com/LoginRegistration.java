package test.com;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

@Test
public class LoginRegistration extends Baseclass {
	// I have wrote this case for failed cause of I have no valid business email to check and verify code. That is why I have not wrote code for assertions to verify.
	// Login will be failed and a folder will be create under screenshot with according to date and a image with test name and time
    @Test (testName = "UserLogin")
	public void login() throws InterruptedException {
		driver.get("https://testtalents.com");
		driver.findElement(By.id("landingHomeLogin")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("recruiterLoginEmail"))).sendKeys("masoma.egov@gmail.com");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("recruiterLoginPassword"))).sendKeys("masoma.egov@gmail.com");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("recruiterLoginFormButton"))).click();

	}
    @Test (testName = "UserRegistration" )
	public void registration() {
		driver.get("https://testtalents.com");
		driver.findElement(By.id("landingHomeStartFree")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("recruiterRegisterFullName"))).sendKeys("Masoma Begum");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("recruiterRegisterEmail"))).sendKeys("masoma.egov@iq.com");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("recruiterRegisterPassword"))).sendKeys("12345678");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("recruiterRegisterConfirmPassword"))).sendKeys("12345678");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("recruiterRegisterFormButton"))).click();

		WebElement ActualTitle = driver.findElement(By.xpath("//h6[contains(text(),'Check your email?')]"));
		String ExpectedTitle = "Check your email? ";
		AssertJUnit.assertEquals(ActualTitle, ExpectedTitle);


	}

}
