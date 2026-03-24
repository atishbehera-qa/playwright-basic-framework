package playwright.tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
public class TestShowHidden {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Establish a connection session with Playwright server
		Playwright playwright=	Playwright.create();
		//Start a respective browser driver according to the browser we want to run our test on.
		Browser browser=playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		//Below statement will create a tab inside browser
		Page page=browser.newPage();
		//Below statement will navigate the browser tab to the application URL
		page.navigate("https://www.letskodeit.com/practice");
		/*
		 * <input id="hide-textbox" class="btn-style class2" value="Hide" onclick="hideElement()" type="submit">
		 */
		// CSS,XPATH,getByRole(),getByLabel(),getByPlaceholder() etc..
		//TAGNAME[ATTRIBUTENAME='ATTRIBUTEVALUE']
		//Below statement will identify button element using CSS
		Locator HideButton=page.locator("input[id='hide-textbox']");
		//Below statement will click on button
		HideButton.click();
		//Below statement will use placeholder attribute to identify an element
		Locator TextBox=page.getByPlaceholder("Hide/Show Example");
		assertThat(TextBox).isHidden();
		
		browser.close();
		playwright.close();
		
	}

}
