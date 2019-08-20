# APIAutomation

API Automation for Weather Bit API

Frameworks, Languages and Tools Used

- Java
- RestAssured
- Apache POI
- Log4j
- Jenkins
- TestNG

The ValidateStateCode and ValidateTimeStampWeather are the primary program files where the API GET logic for running the request for multiple data sets are scripted.Both uses testNG @DataProvider and Apache POI to regress across multiple data sets. There are few utils written for property extraction, logging and for working with Excel. Also TestNG annotations are used and reports are viewable too. The test suite can be can simply be executed as a TestNG suite using the TestNG.xml and reports can be viewed for the data driven test cases. Document for Jenkins integration and SCM polling added for clarity. A toggle has been enabled for @Data provider for switch over between a String Array and a XLSX file. Many other assertions for validating the response time, content-types, status codes and description are also added.

Updated the GIT SCM Polling Document with Steps for Auto Job Trigger.

Program Files:
ValidateStateCode.java
ValidateTimeStampWeather.java

Properties files:
testsettings.properties
log4j.properties

Utilities:
ExcelUtility.java
PropertiesManager.java
LoggerHelper.java

Data Files:
Coordinates.xlsx
PostalCode.xlsx

Config Files:
pom.xml
TestNG.xml
