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

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class HandleSliderElement {

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
		page.navigate("https://jqueryui.com/resources/demos/slider/default.html");
	
	}

	@Test
	public void HandleSlider()
	{
		Locator SliderElement=page.locator("div[id='slider']");
		BoundingBox SliderElementBoundingBox=SliderElement.boundingBox();
		
		double X=SliderElementBoundingBox.x;
		double Y=SliderElementBoundingBox.y;
		double Height=SliderElementBoundingBox.height;
		double Width=SliderElementBoundingBox.width;
		
		double MidX=X+(Width/2);
		double MidY=Y+(Height/2);
		
		double NewX=X+300;
		page.mouse().move(MidX, MidY);
		page.mouse().down();
		page.mouse().move(NewX, MidY);
		page.mouse().up();
		
	}
}
