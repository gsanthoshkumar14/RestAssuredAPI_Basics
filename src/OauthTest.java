
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import Pojo.Api;
import Pojo.GetCourse;
import Pojo.WebAutomation;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OauthTest {
	
	//for serealization & deserealization, we need jackson & gson dependancies
	
	@Test
	public void OuthValidation() throws InterruptedException {
		
		/*
		 * due to google authentication issue, not able to automate the login so did manually but below code can be refered for automating
		 * 
		System.setProperty("webdriver.chrome.driver", "F:\\DriverExeFile\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&Auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php");
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("santhoshkumar.siet@gmail.com");
		driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
		driver.findElement(By.xpath("//span[contains(text(),'More ways to sign in')]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[contains(text(),'Enter your password')]")).click();
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("Alpha@1212");
		driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
		Thread.sleep(1000);
		String url = driver.getCurrentUrl();
		*/
		
		//the below url, got manually by passing the above link given in selenium code
		String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AX4XfWjEABGhHG3IwdWjam7UIUGlzXE4HqUpT75FE8u5skLtVnjsymVNVHP7WfeSnOFCAw&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=1&prompt=none#";
		
		String trimmedurl[] = url.split("&scope");
		String code[] = trimmedurl[0].split("=");
		System.out.println(code[1]);
		
		//to avoid encoding of the code by restassured, use "urlEncodingEnabled(false)"
		String tokenResponse = given().urlEncodingEnabled(false)
				.queryParams("code", code[1])
				.queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
				.queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
				.queryParams("grant_type", "authorization_code")
				.when().log().all()
				.post("https://www.googleapis.com/oauth2/v4/token").asString();
		JsonPath jp = ReusableMethods.jsonParse(tokenResponse);
		String accessToken = jp.getString("access_token");
		System.out.println("-----------------------------------------");
		System.out.println(accessToken);
		
		
		
		
		/*
		 * getting response in string
		 * String finalResponse=given().queryParam("access_token", accessToken)
		.when().log().all()
		.get("https://rahulshettyacademy.com/getCourse.php").asString();
		System.out.println("-----------------------------------------");
		System.out.println(finalResponse);*/
		
		//getting the response as java object using pojo class
		//pojo class can be used for any format, here we are getting json format so we declare the expected format
		//as json by adding the method in given "expect().defaultParser(Parser.JSON)"
		GetCourse gc = given().queryParam("access_token", accessToken).expect().defaultParser(Parser.JSON)
		.when()
		.get("https://rahulshettyacademy.com/getCourse.php").as(GetCourse.class);
		
		String[] courseTitles = {"Selenium Webdriver Java","Cypress","Protractor"};
		
		
		System.out.println(gc.getLinkedIn());
		System.out.println(gc.getInstructor());
		System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());
		
		List<Api> apiCourse = gc.getCourses().getApi();
		
		for(int i=0;i<apiCourse.size();i++) {
			String title = apiCourse.get(i).getCourseTitle();
			if(title.equalsIgnoreCase("SoapUI Webservices testing")) {
				String coursePrice = apiCourse.get(i).getPrice();
				System.out.println(coursePrice);
				break;
			}
		}
		
		List<WebAutomation> webAuto = gc.getCourses().getWebAutomation();
		ArrayList<String> al = new ArrayList<String>();
		for(int i=0;i<webAuto.size();i++) {
			al.add(webAuto.get(i).getCourseTitle());
		}
		
		List<String> expectedList = Arrays.asList(courseTitles);
		
		Assert.assertTrue(al.equals(expectedList));
		
	}

}
