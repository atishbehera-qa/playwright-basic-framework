package playwright.tests;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;

/**
 * Test class for Assignmet 4,5,6
 * @author atish behera
 */
public class AssignmentTest_Two {
	Playwright playwright;
	Browser browser;
	Page page;
	Locator TextBox;
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
	@AfterMethod
	public void Navigate()
	{
		System.out.println("Navigate to the page");
	}
	@BeforeMethod
	public void ClearBrowserCache()
	{
		System.out.println("Clearing browser cache");
	}
	
	//This function covers up to Handle Confirm dialog window (Assignment 4)
		@Test(priority=1)
		public void VerifyConfirmdialogwindow()
		{
			Locator NameField=page.getByPlaceholder("Enter Your Name");
			//Below statement will type the text inside input field
			NameField.fill("Atish");
			
			String typedText=NameField.inputValue();
			
			page.onDialog(alertwindow->
			{
				String alertMessage=alertwindow.message();
				boolean isNamePresent=alertMessage.contains(typedText);
				assertTrue(isNamePresent);
				alertwindow.dismiss();
			}
			);
		}
	//This function covers up to Handle all Radio Buttons (Assignment 5)
				@Test(priority=2)
				public void VerifyallRadiobuttons()
				{
					Locator radioButtons = page.locator("input[type='radio']");
					List<Locator> radioList = radioButtons.all();
					for (Locator radio : radioList) {
					    radio.click();
					    assertTrue(radio.isChecked());
					}
				}
	//This function covers up to Handle Iframe elements (Assignment 6)
				@Test(priority=3)
				public void VerifyIframeElements()
				{
					 page.frameLocator("#courses-iframe")
			            .getByPlaceholder("Search Course")
			            .fill("playwright");

			    page.getByPlaceholder("Start Typing...")
			            .fill("ABC");
				}	
	
			
	     @AfterClass
	     public void CloseTest()
				{
			// This function will take care of Post-requisites
			browser.close();
			playwright.close();
				}
}
