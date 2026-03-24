package playwright.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.util.List;

public class TestCheckboxes {

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
	public void TestCheckbox()
	{
		Locator BMWCheckbox=page.getByLabel(" Benz ");
		BMWCheckbox.click();
		assertThat(BMWCheckbox).isChecked();
	}
	
	@Test(priority=2)
	public void TestMultipleCheckBoxes()
	{
		List<Locator> Checkboxes=page.getByRole(AriaRole.CHECKBOX).all();
		for(Locator Checkbox:Checkboxes)
		{
			Checkbox.click();
			assertThat(Checkbox).isChecked();
		}
	}
}
