import org.testng.Assert;
import org.testng.annotations.Test;

import Files.PayLoad;
import io.restassured.path.json.JsonPath;

public class sumValidation {
	@Test
	public void sumOfValidation() {
		int totalAmt =0;
		JsonPath jp = new JsonPath(PayLoad.mockResponse());
		int courseCount = jp.getInt("courses.size()");
		for(int i=0;i<courseCount;i++) {
			int copies = jp.getInt("courses["+i+"].copies");
			int price = jp.getInt("courses["+i+"].price");
			int amt = copies*price;
			totalAmt = totalAmt+amt; 
		}
		int actualAmt = jp.getInt("dashboard.purchaseAmount");
		Assert.assertEquals(actualAmt, totalAmt);
		
	}
	}

