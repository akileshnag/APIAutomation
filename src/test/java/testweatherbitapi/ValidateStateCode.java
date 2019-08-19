package testweatherbitapi;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import utils.ExcelUtility;
import utils.PropertiesManager;

import static io.restassured.RestAssured.*;
//import static org.hamcrest.Matchers.*;

import java.io.IOException;
//import java.util.Timer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ValidateStateCode extends LoggerHelper {

	Response httpResponse;

	@Test(dataProvider = "Lat-Long Data Provider")
	public void getStateCode(String lati, String longi) throws ParseException {
		String baseURIProp = PropertiesManager.getProperty("baseURI");
		String key = PropertiesManager.getProperty("key");

		httpResponse = given().queryParam("lat", lati).queryParam("lon", longi).queryParam("key", key).when()
				.get(baseURIProp + "/current").then()
				.extract().response();
		
		int statusCode = httpResponse.getStatusCode();
		log.info("\tStatus Code:\t" + statusCode);
		Assert.assertEquals(statusCode, 200);

		String statusLine = httpResponse.getStatusLine();
		log.info("\tStatus Line:\t" + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");

		long respTime = httpResponse.getTime();
		log.info("\tResponse Time:\t" + respTime);

		String contentType = httpResponse.header("Content-Type"); 
		log.info("\tContent Type:\t" + contentType);
		Assert.assertEquals(contentType, "application/json; charset=utf-8");

		String response = httpResponse.getBody().asString();
		// log.info(response);
		JSONParser parse = new JSONParser();
		JSONObject jObj = (JSONObject) parse.parse(response);
		JSONArray jsonArr = (JSONArray) jObj.get("data");
		for (int i = 0; i < jsonArr.size(); i++) {
			JSONObject jsonObject = (JSONObject) jsonArr.get(i);
			double lat = (Double) jsonObject.get("lat");
			double lon = (Double) jsonObject.get("lon");
			String stateCode = (String) jsonObject.get("state_code");
			log.info("*****************State_Code from Response******************");
			log.info("Latitude: " + lat + "\tLongitude: " + lon + "\tState Code: " + stateCode);
		}
		    log.info("***********************************************************");
	}

	/*
	 * Array Implementation of the Data Provider for Test Purposes
	 * 
	 * @DataProvider(name = "Lat-Long Data Provider") String[][] getLatLon() {
	 * String coordinates[][] = { { "40.730610", "-73.935242" }, { "38.123",
	 * "-78.543" }, { "38.9072", "77.0369" }, { "27.6648", "81.5158" }, { "31.9686",
	 * "99.9018" }};
	 * 
	 * return (coordinates); }
	 */

	@DataProvider(name = "Lat-Long Data Provider")
	String[][] provideLatLon() throws IOException {
		String filePath = "C:\\Users\\Akilesh Nagarajan\\eclipse-workspace\\API_Automation\\src\\test\\resources\\Coordinates.xlsx";
		int rowCount = ExcelUtility.getRowCount(filePath, "Sheet1");
		int colCount = ExcelUtility.getCellCount(filePath, "Sheet1", 1);
        
		log.info("*********Excel Data*********");
		log.info("Row Count: "+rowCount);
		log.info("Column Count: "+colCount);
		log.info("****************************");
		
		String coordinates[][] = new String[rowCount][colCount];

		for (int i = 1; i <= rowCount; i++) {

			for (int j = 0; j < colCount; j++) {

				coordinates[i - 1][j] = (String) ExcelUtility.getCellData(filePath, "Sheet1", i, j);
				// log.info(coordinates[i-1][j]);
			}

		}

		return (coordinates);

	}

}
