import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*; // for equalTo method since it is static we need to add manually

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.testng.Assert;

import Files.PayLoad;

public class Basics {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		// Add place API validation
		
		//3 methods in rest api testing
		//Given - all the inputs are provided in this method
		//When - the request is submitted in this method - resource & http method should be declared in when remaining goes in given
		//Then - Valiation of the request is done here by the response we get
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		//given is a static method which will not populate automatically to import
		//so we will add the import manually with static keyword "import static io.restassured.RestAssured.*;" 
		// external json file also can be given as input by converting json to byte then byte to string
		//add API
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body(new String(Files.readAllBytes(Path.of("G:\\Notepad++\\AddPlace.json")))).when().post("/maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP")).header("Connection", "Keep-Alive")
		.extract().response().asString();
		
		System.out.println("---------------------------------------");
		
		System.out.println(response);
		
		JsonPath jp = new JsonPath(response);
		String placeId = jp.getString("place_id");
		
		System.out.println("---------------------------------------");
		
		System.out.println(placeId);
		
		System.out.println("---------------------------------------");
		
		String newAddress = "70 winter walk, USA";
		
		//update API
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body(PayLoad.updatePlace(placeId, newAddress)).when().put("/maps/api/place/update/json")
		.then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		//get API
		String newResponse = given().log().all().queryParam("place_id", placeId).queryParam("key", "qaclick123")
		.get("/maps/api/place/get/json")
		.then().assertThat().body("address", equalTo(newAddress)).extract().asString();
		
		JsonPath jp1 = new JsonPath(newResponse);
		
		String updAddress = jp1.getString("address");
		
		System.out.println("---------------------------------------");
		System.out.println(updAddress);
		
		Assert.assertEquals(newAddress, updAddress);
		

	}

}
