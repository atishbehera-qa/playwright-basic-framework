package playwright.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;

public class GetRequest {
	APIRequestContext request;
	@BeforeClass
	public void StartTest()
	{
		Playwright playwright = Playwright.create();
		request = playwright.request().newContext();
	}
	
	@Test
	public void TestGetUserDetails()
	{
		
		APIResponse response=request.get("http://localhost:8080/api/users/1");
		int StatusCode=response.status();
		Assert.assertEquals(StatusCode, 200,"Get request failed");
		String UserDetails=response.text();
		System.out.println(UserDetails);
		Map<String,String>Headers=response.headers();
		for(String Key:Headers.keySet())
		{
			System.out.println(Headers.get(Key));
		}
	}
}
