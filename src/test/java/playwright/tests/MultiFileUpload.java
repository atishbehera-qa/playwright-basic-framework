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
import com.microsoft.playwright.options.SelectOption;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.nio.file.Path;
import java.nio.file.Paths;

public class MultiFileUpload {


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
		page.navigate("https://www.way2automation.com/way2auto_jquery/registration.php#load_box");
	
	}

	@Test
	public void MultiFileUpload()
	{
		FrameLocator frame=page.frameLocator("iframe[id='iframeResult']");
		//new Path[] {Paths.get("file1.txt"), Paths.get("file2.txt")}
		frame.locator("input[type='file']").setInputFiles(new Path[] 
				{
						Paths.get("D:\\Pranoday\\Ness_Playwright_Java_Batch\\Post_Request.java"), 
						Paths.get("D:\\Pranoday\\Ness_Playwright_Java_Batch\\TraceViewer.java")
				}
		);
	}
}
