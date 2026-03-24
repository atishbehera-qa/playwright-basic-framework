package playwright.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.nio.file.Paths;

/*
 * 	@BeforeSuite
 *  @BeforeTest
 *  @AfterTest
 *  @BeforeMethod
 *  @AfterMethod
 *  @BeforeClass
 *  @AfterClass
 *  @Test
 */
public class TestEnableDisableFunctionality {
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
		BrowserContext browsercontext=browser.newContext(
				new Browser.NewContextOptions().setRecordVideoDir(Paths.get("./Videos")));
		//Below statement will create a tab inside browser
		page=browsercontext.newPage();
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
	@Test(priority=1)
	public void TestDisableButton()
	{
		//This function covers up Disable button test case
		/*
		 *	//input[@value='Disable']
		 */
		//Locator DisableButton=page.locator("//input[@value='Disable']");
		Locator DisableButton=page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("Disable"));
		DisableButton.click();
		TextBox=page.locator("//input[@id='enabled-example-input']");
		assertThat(TextBox).isDisabled();
	}
	@Test(priority=2)
	public void TestEnableButton()
	{
		//This function covers up Enable button test case
	}
	
	@AfterClass
	public void CloseTest()
	{
		// This function will take care of Post-requisites
		browser.close();
		playwright.close();
	}
}
