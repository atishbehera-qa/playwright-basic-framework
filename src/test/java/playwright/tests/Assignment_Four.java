package playwright.tests;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

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
 * Test class for Assignmet 9
 * @author atish behera
 */

public class Assignment_Four {
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
	{System.out.println("Navigate to the page");
	}
	@BeforeMethod
	public void ClearBrowserCache()
	{System.out.println("Clearing browser cache");
	}
	
	//This function covers up Handle window opened in new browser tab (Assignment 9)
		@Test
		public void VerifyHomeLinkOnNewTab()
		{
			Locator OpenWindowButton=page.locator("#openwindow");
			Page newPage = page.waitForPopup(() -> {
			    OpenWindowButton.click();
			});

			newPage.locator("a[href='/home']").click();
		
		}
	
	 @AfterClass
     public void CloseTest()
			{
		// This function will take care of Post-requisites
		browser.close();
		playwright.close();
			}
}
