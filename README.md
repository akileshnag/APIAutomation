# APIAutomation

API Automation for Weather Bit API

Frameworks, Languages and Tools Used

- Java
- RestAssured
- Apache POI
- Log4j
- Jenkins
- TestNG

The ValidateStateCode is the primary java file where the API GET logic for running the request for multiple data sets is scripted.
The ValidateStateCode uses testNG @DataProvider and Apache POI to run across multiple data sets. There are few utils written for property validation, logging and for working with Excel. Also TestNG annotations are used and reports are viewable too. The ValidateState code can simply be executed as a TestNG suite and reports can be viewed for the data driven TCS. Snippets for Jenkins integration and SCM polling added for clarity. A toggle has been enabled for @Data provider for switch over between a String Array and a XLSX file. Many other assertions for validating the response time, content-types, status codes and desctiption are also added.

The ValidateTimeStampWeather API tests could not be implemented as WeatherBit has blocked the API access outside the Free Membership usage and can be used only for Error Validation and most parts of datadriven and frameworking is implemented with ValidateStateCode. Snippets added for clarity.
