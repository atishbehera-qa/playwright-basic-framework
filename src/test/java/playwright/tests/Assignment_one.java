package playwright.tests;

import static 
com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat; 

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
* Test class for Assignmet 1,2 & 3 
* @author atish behera 
*/ 

public class Assignment_one { 
Playwright playwright; 
Browser browser; 
Page page; 
Locator TextBox; 
@BeforeClass 
public void StartTest() 
{ 
 playwright= Playwright.create(); 
 //Start a respective browser driver according to the browser we want to run our test on. 
 browser=playwright.chromium().launch(new 
BrowserType.LaunchOptions().setHeadless(false)); 
 //Below statement will create a tab inside browser 
 page=browser.newPage(); 
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
 

//This function covers up Show button test case (Assignment 1) 
@Test(priority=1) 
public void VerifyHideButton() 
{ 
  
 Locator HideButton=page.getByRole(AriaRole.BUTTON, new 
Page.GetByRoleOptions().setName("Hide")); 
 HideButton.click(); 
 Locator textBox = page.getByPlaceholder("Hide/Show Example"); 
 assertThat(textBox).isHidden(); 
 //click on Show Button 
 Locator ShowButton=page.getByRole(AriaRole.BUTTON, new 
Page.GetByRoleOptions().setName("Show")); 
 ShowButton.click(); 
 assertThat(textBox).isVisible();  
} 
//This function covers up Enabled/Disable button test case (Assignment 2) 
@Test(priority=2) 
public void VerifyEnabledbutton() 
{ 
  
 Locator disableButton=page.getByRole(AriaRole.BUTTON, new 
Page.GetByRoleOptions().setName("Disable")); 
 disableButton.click(); 
 Locator textBox = page.getByPlaceholder("Enabled/Disabled Field"); 
 assertThat(textBox).isDisabled(); 
 //Click on enabled Button 
 Locator enableButton=page.getByRole(AriaRole.BUTTON, new 
Page.GetByRoleOptions().setName("Enable")); 
 enableButton.click(); 
 assertThat(textBox).isEnabled(); 
} 
 
//This function covers up Home hyperlink works or not test cases (Assignment 3) 
@Test(priority=3) 
public void VerifyHomebutton() 
{ 
  
Locator homeLink = page.getByRole(AriaRole.LINK,new 
Page.GetByRoleOptions().setName("HOME")).first(); 
homeLink.click(); 
assertThat(page).hasURL("https://www.letskodeit.com/home"); 
 
} 
 
 
@AfterClass 
public void CloseTest() 
{ 
 // This function will take care of Post-requisites 
 browser.close(); 
 playwright.close(); 
} 
}
