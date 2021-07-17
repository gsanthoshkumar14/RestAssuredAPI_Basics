import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import Pojo.GMap;
import Pojo.Location;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilderTest {

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
		
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
		.setContentType(ContentType.JSON).build();
		
		ResponseSpecification resp = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		
		RequestSpecification res = given().spec(req).body(gm);
		
		String output = res.when()
		.post("/maps/api/place/add/json")
		.then().spec(resp).extract().response().asString();
		
		
		System.out.println(output);
	}

}
