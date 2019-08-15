package testweatherbitapi;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
//import io.restassured.http.Header;
//import io.restassured.http.Headers;
import io.restassured.http.Method;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.ExcelUtility;
import utils.PropertiesManager;

import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ValidateStateCode extends LoggerHelper {

	@Test(dataProvider = "Lat-Long Data Provider")
	void getStateCode(String lat, String lon) throws ParseException {
		lat = lat.trim();
		lon = lon.trim();
		String baseURIProp = PropertiesManager.getProperty("baseURI");
		RestAssured.baseURI = baseURIProp;
		RequestSpecification httpRequest = RestAssured.given();
		String key = PropertiesManager.getProperty("key");
		Response httpResponse = httpRequest.request(Method.GET, "/current?lat=" + lat + "&lon=" + lon + "&key=" + key);

		String responseBody = httpResponse.getBody().asString();
		log.info("Response:\n" + responseBody);

		int statusCode = httpResponse.getStatusCode();
		log.info("Status Code: " + statusCode);
		Assert.assertEquals(statusCode, 200);

		String statusLine = httpResponse.getStatusLine();
		log.info("Status Line: " + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		
		long respTime= httpResponse.getTime();
		log.info("Response Time: "+respTime);

		/*
		 * String contentType = httpResponse.header("Content-Type"); //
		 * log.info("Content Type: \n" + contentType); Assert.assertEquals(contentType,
		 * "application/json; charset=utf-8");
		 * 
		 * Headers allHeaders = httpResponse.headers(); for (Header header : allHeaders)
		 * { // log.info("|" + header.getName() + "||" + header.getValue() + "|"); }
		 */

		Assert.assertEquals(responseBody.contains("state_code"), true);

		String response = httpResponse.getBody().asString();
		// log.info(response);
		JSONParser parse = new JSONParser();
		JSONObject jObj = (JSONObject) parse.parse(response);
		JSONArray jsonArr = (JSONArray) jObj.get("data");
		for (int i = 0; i < jsonArr.size(); i++) {
			JSONObject jsonObject = (JSONObject) jsonArr.get(i);
			String stateCode = (String) jsonObject.get("state_code");
			log.info("State Code: " + stateCode);
		}

	}

	@DataProvider(name = "Lat-Long Data Provider")
	String[][] getLatLon() {
		String cordinates[][] = { { "40.730610", "-73.935242" }, { "38.123", "-78.543" }, { "38.9072", "77.0369" },
				{ "27.6648", "81.5158" }, { "31.9686", "99.9018" }, { "40.730610", "-73.935242" }, { "38.123", "-78.543" }, { "38.9072", "77.0369" },
				{ "40.730610", "-73.935242" }, { "38.123", "-78.543" }, { "38.9072", "77.0369" }};
		return (cordinates);
	}

	/*
	 * @DataProvider(name = "Lat-Long Data Provider") String[][] getLatLon() throws
	 * IOException { String filePath =
	 * "C:\\Users\\Akilesh Nagarajan\\eclipse-workspace\\API_Automation\\src\\test\\resources\\Coordinates.xlsx"
	 * ; int rowCount = ExcelUtility.getRowCount(filePath, "Sheet1"); int colCount =
	 * ExcelUtility.getCellCount(filePath, "Sheet1", 1);
	 * 
	 * log.info(rowCount); log.info(colCount);
	 * 
	 * String cordinates[][] = new String[rowCount][colCount];
	 * 
	 * for (int i = 1; i <= rowCount; i++) {
	 * 
	 * for (int j = 0; j < colCount; j++) {
	 * 
	 * cordinates[i - 1][j] = ExcelUtility.getCellData(filePath, "Sheet1", i, j);
	 * log.info(cordinates); }
	 * 
	 * } return (cordinates);
	 * 
	 * }
	 */

}
