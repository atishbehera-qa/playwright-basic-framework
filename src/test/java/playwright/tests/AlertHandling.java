package playwright.tests;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class AlertHandling {

	Playwright playwright;
	Browser browser;
	Page page;
	
	@BeforeClass
	public void StartTest()
	{
		playwright=	Playwright.create();
		//Start a respective browser driver according to the browser we want to run our test on.
		browser=playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		//Below statement will create a tab inside browser
		page=browser.newPage();
		//Below statement will navigate the browser tab to the application URL
		page.navigate("https://www.letskodeit.com/practice");
	
	}
	
	@Test(priority=1)
	public void TestAlertMessage()
	{
		/*
		 * (num1,num2)=>
		 * {
		 * 		dfdfdff
		 * }
		 */
		
		Locator NameField=page.locator("input#name");
		//Below statement will type the text inside input field
		NameField.fill("Pranoday");
		String TypedText=NameField.getAttribute("value");
		
		page.onDialog(alertwindow->
		{
			String AlertMessage=alertwindow.message();
			boolean IsNamePresent=AlertMessage.contains(TypedText);
			assertTrue(IsNamePresent);
			alertwindow.accept();
		}
		
		);
		Locator AlertButton=page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("Alert"));
		AlertButton.click();
		
	}
	
	@AfterClass
	public void CloseTest()
	{
		// This function will take care of Post-requisites
		browser.close();
		playwright.close();
	}
}
