package testweatherbitapi;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeSuite;

import utils.PropertiesManager;

public class LoggerHelper {

	public static final Logger log = Logger.getLogger(LoggerHelper.class);
	protected static String key;

	@BeforeSuite(alwaysRun = true)
	public void BeforeSuite() throws Exception {
		PropertyConfigurator.configure("src\\main\\resources\\log4j.properties");
		PropertiesManager.initializeProperties();
		log.info("Properties Initialized");

		

	}
}
