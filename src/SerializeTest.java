import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import Pojo.GMap;
import Pojo.Location;


public class SerializeTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		GMap gm = new GMap();
		Location l = new Location();
		
		l.setLat(-38.383494);
		l.setLng(33.427362);
		
		
		List<String> typeList = new ArrayList<String>();
		typeList.add("shoe park");
		typeList.add("shop");
		
		gm.setAccuracy(50);
		gm.setName("Frontline house");
		gm.setPhone_number("(+91) 983 893 3937");
		gm.setAddress("29, side layout, cohen 09");
		gm.setWebsite("http://google.com");
		gm.setLanguage("French-IN");
		gm.setTypes(typeList);
		gm.setLocation(l);
		
		

		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		Response res = given().queryParam("key", "qaclick123").body(gm)
		.when()
		.post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).extract().response();
		
		String response = res.asString();
		System.out.println(response);
	}

}
