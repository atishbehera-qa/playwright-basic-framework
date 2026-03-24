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

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.util.List;

public class IframeHandling {
	
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
	
	public void CheckIframeElement()
	{
		/*		
		 * 	<html>
		 * 			sdsddsd
		 * 			sfdfdf
		 * 	</html>
		 * 
		 * 	<html>
		 * 		<iframe>
		 * 			<html>
		 * 			</html>
		 * 		</iframe>
		 *  </html> 	
		 */
		
		FrameLocator Iframe=page.frameLocator("iframe[id='iframeResult']");
		
		Locator FirstNameField=Iframe.locator("input[name='fname']");
		FirstNameField.fill("Pranoday");
		
		Locator LastNameField=Iframe.locator("input[name='lname']");
		LastNameField.fill("Dingare");
		
		Locator SubmitButton=Iframe.getByRole(AriaRole.BUTTON);
		SubmitButton.click();
		
		Locator RunButton=page.locator("button[id='runbtn']");
		RunButton.click();
	}
}
