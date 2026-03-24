package playwright.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeClass;

import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;

public class PostRequest {
	APIRequestContext request;
	@BeforeClass
	public void StartTest()
	{
		Playwright playwright = Playwright.create();
		request = playwright.request().newContext();
	}
	
	public void TestPostRequest()
	{
		
		Map<String,Object> data = new HashMap<>();
		data.put("email", "trump@way2automation.com");
		data.put("firstName", "Donald");
		data.put("lastName", "Trump");
		
		APIResponse response=request.post("http://localhost:8080/api/users",RequestOptions.create().setData(data));
		System.out.println(response.status());
	}
}
