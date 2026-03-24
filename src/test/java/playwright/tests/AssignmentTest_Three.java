package playwright.tests;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.SelectOption;

/**
 * Test class for Assignmet 7 & 8
 * @author atish behera
 */
public class AssignmentTest_Three {
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
	}	
	@AfterMethod
	public void Navigate()
	{System.out.println("Navigate to the page");
	}
	@BeforeMethod
	public void ClearBrowserCache()
	{System.out.println("Clearing browser cache");
	}
	
	//This function covers up to Handle Select elements (Assignment 7)
	@Test(priority=1)
	public void VerifySelectelements()
	{
		page.navigate("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_select");
		FrameLocator frame = page.frameLocator("#iframeResult");
		// Select by INDEX
		frame.locator("#cars").selectOption(new SelectOption().setIndex(1));
		// Select by VALUE
		frame.locator("#cars").selectOption(new SelectOption().setValue("opel"));
		// Select by LABEL
		frame.locator("#cars").selectOption(new SelectOption().setLabel("Audi"));	
	}
//This function covers up to Handle MultiSelectElement (Assignment 8)
	@Test(priority=2)
	public void VerifyMultiSelectelement()
	{
		 page.navigate("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_select_multiple");

		    // switch to iframe
		    FrameLocator frame = page.frameLocator("#iframeResult");

		    // Select multiple options
		    frame.locator("#cars").selectOption(new String[]{"volvo","opel","audi"});
	}
	 @AfterClass
     public void CloseTest()
			{
		// This function will take care of Post-requisites
		browser.close();
		playwright.close();
			}
}
