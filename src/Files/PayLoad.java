package Files;

public class PayLoad {
	
	public static String addPlace() {
		String addTxt = "{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -38.383494,\r\n"
				+ "    \"lng\": 33.427362\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": 50,\r\n"
				+ "  \"name\": \"Frontline house\",\r\n"
				+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
				+ "  \"address\": \"29, side layout, cohen 09\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"shoe park\",\r\n"
				+ "    \"shop\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"http://google.com\",\r\n"
				+ "  \"language\": \"French-IN\"\r\n"
				+ "}\r\n"
				+ "";
			
		return addTxt;
	}
	
	public static String updatePlace(String placeId, String address) {
		String updTxt = "{\r\n"
				+ "\"place_id\":\""+placeId+"\",\r\n"
				+ "\"address\":\""+address+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}\r\n"
				+ "";
		
		return updTxt;		
	}

	public static String mockResponse() {
		String mockRes = "{\r\n"
				+ "\"dashboard\": {\r\n"
				+ "\"purchaseAmount\": 1450,\r\n"
				+ "\"website\": \"rahulshettyacademy.com\"\r\n"
				+ "},\r\n"
				+ "\"courses\": [\r\n"
				+ "{\r\n"
				+ "\"title\": \"Selenium Python\",\r\n"
				+ "\"price\": 50,\r\n"
				+ "\"copies\": 6\r\n"
				+ "},\r\n"
				+ "{\r\n"
				+ "\"title\": \"Cypress\",\r\n"
				+ "\"price\": 40,\r\n"
				+ "\"copies\": 4\r\n"
				+ "},\r\n"
				+ "{\r\n"
				+ "\"title\": \"RPA\",\r\n"
				+ "\"price\": 45,\r\n"
				+ "\"copies\": 10\r\n"
				+ "},\r\n"
				+ "{\r\n"
				+ "\"title\": \"Appium\",\r\n"
				+ "\"price\": 36,\r\n"
				+ "\"copies\": 15\r\n"
				+ "}\r\n"
				+ "]\r\n"
				+ "}\r\n"
				+ "";
		
		return mockRes;
				
	}
	
	public static String addBookJson(String aisle, String isbn) {
		String jsonBook = "{\r\n"
				+ "\r\n"
				+ "\"name\":\"Learn Selenium Automation with Core Java\",\r\n"
				+ "\"isbn\":\""+isbn+"\",\r\n"
				+ "\"aisle\":\""+aisle+"\",\r\n"
				+ "\"author\":\"Santhosh kumar\"\r\n"
				+ "}\r\n"
				+ "";
		return jsonBook;
	}
	
	public static String deleteBookJson(String id) {
		String jsonId = "{\r\n"
				+ " \r\n"
				+ "\"ID\" : \""+id+"\"\r\n"
				+ " \r\n"
				+ "} \r\n"
				+ "";
		return jsonId;
	}
}
