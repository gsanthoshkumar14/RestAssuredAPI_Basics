import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;

public class JiraRestAPI {
	
	@Test
	public void addComment() {
		
		SessionFilter session = new SessionFilter();
		RestAssured.baseURI = "http://localhost:8080";
		
		//generate session
		//for handling https certification use "relaxedHTTPSValidation()" in given part
		String response = given().relaxedHTTPSValidation().header("Content-Type", "application/json")
				.body("{ \"username\": \"gsanthoshkumar141089\", \"password\": \"Alpha@1212\" }")
				.filter(session)
				.when().post("/rest/auth/1/session/").then()
				.extract().response().asString();
		
		
		//add comment
		String actualComment = "Added the comment from script to retrieve";
		String commentResponse = given().log().all().pathParam("id", "10000").header("Content-Type", "application/json").body("{\r\n"
				+ "    \"body\": \""+actualComment+"\",\r\n"
				+ "    \"visibility\": {\r\n"
				+ "        \"type\": \"role\",\r\n"
				+ "        \"value\": \"Administrators\"\r\n"
				+ "    }\r\n"
				+ "}")
		.filter(session)
		.when().post("/rest/api/2/issue/{id}/comment")
		.then().assertThat().statusCode(201).extract().response().asString();
		JsonPath jp = ReusableMethods.jsonParse(commentResponse);
		String commentId = jp.getString("id");
		System.out.println(commentId);
		System.out.println("------------------------------------------");
		
		//add attachment
		//while attaching a file, we have to use multipart and if we use multipart have to include the below header
		//header("Content-Type", "multipart/form-data")
		given().header("X-Atlassian-Token", "no-check").filter(session).pathParam("id", "10000")
		.header("Content-Type", "multipart/form-data").multiPart("file",new File("jira.txt"))
		.when().post("/rest/api/2/issue/{id}/attachments").then().assertThat().statusCode(200);
		
		//get bug detail
		String bugResponse = given().filter(session).pathParam("id", "10000")
				.queryParam("fields", "comment")
		.when().get("/rest/api/2/issue/{id}")
		.then().extract().response().asString();
		JsonPath jp1 = ReusableMethods.jsonParse(bugResponse);
		int commentCount = jp1.getInt("fields.comment.comments.size()");
		for (int i=0;i<commentCount;i++) {
			String newCommentId = jp1.getString("fields.comment.comments["+i+"].id");
			System.out.println(newCommentId);
			if(newCommentId.equals(commentId)) {
				String commentTxt = jp1.getString("fields.comment.comments["+i+"].body");
				System.out.println(commentTxt);
				Assert.assertEquals(actualComment, commentTxt);
			}
		}
	}

}
