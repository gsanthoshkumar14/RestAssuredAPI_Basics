import Files.PayLoad;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JsonPath jp = new JsonPath(PayLoad.mockResponse());
		
		/*1. Print No of courses returned by API
		2.Print Purchase Amount
		3. Print Title of the first course
		4. Print All course titles and their respective Prices
		5. Print no of copies sold by RPA Course
		6. Verify if Sum of all Course prices matches with Purchase Amount*/
		
		//1. Print No of courses returned by API
		
		int courseCount = jp.getInt("courses.size()");
		System.out.println(courseCount);
		
		//2.Print Purchase Amount
		
		int purAmt = jp.getInt("dashboard.purchaseAmount");
		System.out.println(purAmt);
		
		//3. Print Title of the first course
		
		String title1 = jp.getString("courses[0].title");
		System.out.println(title1);
		
		//4. Print All course titles and their respective Prices
		
		for(int i=0;i<courseCount;i++) {
			String title = jp.getString("courses["+i+"].title");
			int price = jp.getInt("courses["+i+"].price");
			System.out.println(title+" : "+price);
		}
		
		//5. Print no of copies sold by RPA Course
		
		for(int i=0;i<courseCount;i++) {
			String title = jp.getString("courses["+i+"].title");
			if(title.contains("RPA")) {
			int copies = jp.getInt("courses["+i+"].copies");
			System.out.println(title+" : "+copies);
			break;
			}
		}

		//6. Verify if Sum of all Course prices matches with Purchase Amount
		int totalAmt =0;
		for(int i=0;i<courseCount;i++) {
			int copies = jp.getInt("courses["+i+"].copies");
			int price = jp.getInt("courses["+i+"].price");
			int amt = copies*price;
			totalAmt = totalAmt+amt; 
		}
		System.out.println(totalAmt);
		
	}

}
