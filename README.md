# APIAutomation

API Automation for Weather Bit API

Frameworks, Languages and Tools Used

- Java
- RestAssured
- Apache POI
- Log4j
- Jenkins
- TestNG

The ValidateStateCode is the primary java files where the API GET logic running it across multiple data sources is scripted.
The ValidateStateCode the testNG @DataProvider and Apache POI to run across multiple data sets. There are few utils written around for property validation
and for working with Excel. The ValidateState code is simple to be executed as a TestNG suite and reports can be viewed for the data driven TCS.
A toggle has been enabled for @Data provider for switch over between a String Array and a XLSX file.
