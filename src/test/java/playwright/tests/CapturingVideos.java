package playwright.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.BoundingBox;
import com.microsoft.playwright.options.SelectOption;
import com.microsoft.playwright.BrowserContext;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.nio.file.Paths;

public class CapturingVideos {

	Playwright playwright;
	Browser browser;
	Page page;
	
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
		page.navigate("https://www.wikipedia.org/");
		

	}

	@Test
	public void Test()
	{
		
		//select by value

				page.selectOption("select", "hi");
				//select by text

				page.selectOption("select", new SelectOption().setLabel("Eesti"));
	}
}
