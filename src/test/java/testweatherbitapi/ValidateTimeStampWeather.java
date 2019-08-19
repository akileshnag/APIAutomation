package testweatherbitapi;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import utils.ExcelUtility;
import utils.PropertiesManager;

public class ValidateTimeStampWeather extends LoggerHelper {

	Response httpResponse;

	@Test(dataProvider = "PostCode Data Provider")
	public void getTempDateTime(String postCode) throws ParseException {
		String baseURIProp = PropertiesManager.getProperty("baseURI");
		String key = PropertiesManager.getProperty("key");
        
		httpResponse = given().queryParam("postal_code", postCode).queryParam("key", key).when()
				.get(baseURIProp + "/forecast/daily").then().extract().response();
        
	
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
		

		log.info("****************16 Days Weather Forecast for PostCode " + postCode + " *************");
		for (int i = 0; i < jsonArr.size(); i++) {
			JSONObject jsonObject = (JSONObject) jsonArr.get(i);
			String dateTime = (String) jsonObject.get("datetime");
			String desc = "";
			JSONObject weat = (JSONObject) jsonObject.get("weather");
			desc = weat.get("description").toString();

			log.info("\tDateTime: " + dateTime + "\tWeather: " + desc);
		
			

		}
		

		log.info("************************************************************************************");
		
		} 
		


	/*
	 * @DataProvider(name="PostCode Data Provider")
	 * 
	 * String[][] providePostalCode() { String postCode[][] = { { "27601", "1" }, {
	 * "27021", "1" }, { "27010", "1" }, { "27014", "1" }, { "27019", "1" } };
	 * 
	 * return (postCode); }
	 */

	@DataProvider(name = "PostCode Data Provider")
	String[][] providePostalCode() throws IOException {
		String filePath = "C:\\Users\\Akilesh Nagarajan\\eclipse-workspace\\API_Automation\\src\\test\\resources\\PostalCode.xlsx";
		int rowCount = ExcelUtility.getRowCount(filePath, "Sheet1");
		int colCount = ExcelUtility.getCellCount(filePath, "Sheet1", 1);

		log.info("*********Excel Data*********");
		log.info("Row Count: " + rowCount);
		log.info("Column Count: " + colCount);
		log.info("****************************");

		String postCode[][] = new String[rowCount][colCount];

		for (int i = 1; i <= rowCount; i++) {

			for (int j = 0; j < colCount; j++) {

				postCode[i - 1][j] = (String) ExcelUtility.getCellData(filePath, "Sheet1", i, j);
				// log.info(postCode[i-1][j]);
			}

		}

		return (postCode);

	}

}
