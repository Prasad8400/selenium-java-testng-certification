package SeleniumJavaTestNGAssignment;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class TestScenarios extends BaseClass{

	
	@Test (priority=1)
	public void assign1_DOMElements() {

		/*
		 1. Perform an explicit wait till the time all the elements in the DOM are available.
	     2. Use the SoftAssert to validate the Page Title. Validate Against “LambdaTest” 
	        (expecting a failure and proceeding to the following statement).
		 */

		// Perform an explicit wait to wait until all the elements are available in the DOM
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		By DOMElements = By.cssSelector(".list-disc a");

		List<WebElement> elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(DOMElements));

		if (elements.size() > 0) {
			System.out.println("All elements are present in the DOM!");
		}

		SoftAssert softAssert = new SoftAssert();
		String pageTitle = driver.getTitle();
		softAssert.assertEquals(pageTitle, "LambdaTest"); //Failure

		System.out.println("SoftAssert has finished the validation for the title."); //Proceed with next statement

		softAssert.assertAll();

	}

	@Test (priority=2)
	public void assign2_CheckboxDemo() {

		/*
		     1. Click “Checkbox Demo”.
			 2. Click the checkbox under the “Single Checkbox Demo” section.
			 3. Validate whether this checkbox is “selected”.
			 4. Now click the checkbox again and validate whether the checkbox is “unselected”.
		 */
		WebElement CheckboxDemo = driver.findElement(By.linkText("Checkbox Demo"));
		CheckboxDemo.isDisplayed();
		CheckboxDemo.click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		WebElement SingleCheckBoxDemo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("isAgeSelected")));
		//WebElement SingleCheckBoxDemo = driver.findElement(By.id("isAgeSelected"));
		SingleCheckBoxDemo.click();
		Assert.assertTrue(SingleCheckBoxDemo.isSelected());

		SingleCheckBoxDemo.click();
		Assert.assertFalse(SingleCheckBoxDemo.isSelected());

	}
	@Test(priority=3)
	public void assign3_JavascriptAlerts() {

		/*
		 1. Launch the same browser and URL given in the Pre-Condition.
	     2. Click “Javascript Alerts”.
		 3. Now click the “Click Me” button in the “JavaScript Alerts” section.
		 4. Validate the alert message “I am an alert box!” and click OK.
		 */

		WebElement JavascriptAlerts = driver.findElement(By.linkText("Javascript Alerts"));
		JavascriptAlerts.isDisplayed();
		JavascriptAlerts.click();

		driver.findElement(By.xpath("//*[text()='JavaScript Alerts']/descendant::button[@type='button']")).click();

		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
	     wait.until(ExpectedConditions.alertIsPresent());
	        
		String alertMsg = driver.switchTo().alert().getText();
		Assert.assertEquals("I am an alert box!", alertMsg,"Assertion Failed Msg Does not match:");

		driver.switchTo().alert().accept();

	}
}
