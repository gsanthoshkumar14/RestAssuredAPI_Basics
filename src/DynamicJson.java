import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Files.PayLoad;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJson {

	@Test (dataProvider = "BookData")
	public void addBook(String aisle, String isbn) {
		RestAssured.baseURI = "http://216.10.245.166";
		String response = given().header("Content-Type", "application/json").body(PayLoad.addBookJson(aisle, isbn))
				.when().post("Library/Addbook.php").then().extract().asString();
		JsonPath jp = ReusableMethods.jsonParse(response);
		String id = jp.get("ID");
		System.out.println(id);
		
		String response1 = given().header("Content-Type", "application/json").body(PayLoad.deleteBookJson(id))
				.when().post("/Library/DeleteBook.php").then().extract().asString();
		JsonPath jp1 = ReusableMethods.jsonParse(response1);
		String msg = jp1.getString("msg");
		System.out.println(msg);
	}
	
	
	@DataProvider (name="BookData")
	public Object[][] getData() {
		return new Object[][] {{"123456780","abcdef"},{"123450618","abcdef"},{"112304569","abcdef"}};
	}
}
