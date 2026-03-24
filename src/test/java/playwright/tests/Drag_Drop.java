package playwright.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.BoundingBox;
import com.microsoft.playwright.options.SelectOption;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.nio.file.Paths;

public class Drag_Drop {

	Playwright playwright;
	Browser browser;
	Page page;
	BrowserContext Context;
	@BeforeClass
	public void StartTest()
	{
		playwright=	Playwright.create();
		//Start a respective browser driver according to the browser we want to run our test on.
		browser=playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		Context=browser.newContext();
		Context.tracing().start(new Tracing.StartOptions().setScreenshots(true).setSources(true).setSnapshots(true));	
		//Below statement will create a tab inside browser
		page=Context.newPage();
		//Below statement will navigate the browser tab to the application URL
		page.navigate("https://jqueryui.com/resources/demos/droppable/default.html");
	
	}

	@Test
	public void Drag_Drop()
	{
		
		Locator DraggableElement=page.locator("div[id='draggable']");
		
		BoundingBox DraggableElementBox=DraggableElement.boundingBox();
		double DraggableX=DraggableElementBox.x;
		double DraggableY=DraggableElementBox.y;
		double DraggableWidth=DraggableElementBox.width;
		double DraggableHeight=DraggableElementBox.height;
		
		double DraggableMidX=DraggableX+(DraggableWidth/2);
		double DraggableMidY=DraggableY+(DraggableHeight/2);
		
		Locator DroppableElement=page.locator("div[id='droppable']");
		BoundingBox DroppableElementBoundingBox=DroppableElement.boundingBox();
		double DroppableX=DroppableElementBoundingBox.x;
		double DroppableY=DroppableElementBoundingBox.y;
		double DroppableWidth=DroppableElementBoundingBox.width;
		double DroppableHeight=DroppableElementBoundingBox.height;
		
		double DroppableMidX=DroppableX+(DroppableWidth/2);
		double DroppableMidY=DroppableY+(DroppableHeight/2);
		
		page.mouse().move(DraggableMidX, DraggableMidY);
		//Statement below will press and hold LEFT mouse button
		page.mouse().down();
		page.mouse().move(DroppableMidX, DroppableMidY);
		//Statement below will release the LEFT mouse button which was pressed and hold. 
		page.mouse().up();
		Context.tracing().stop(new Tracing.StopOptions().setPath(Paths.get("Trace.zip")));
		
	}
}
