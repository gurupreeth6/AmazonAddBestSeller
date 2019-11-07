# AmazonAddBestSeller
Selenium script to automate adding best sellers to the cart 

# File Overview
* `/Configuration`
  * `config.properties` Contains base URL and path of chrome driver and firefox driver
* `/drivers`
  * `chromedriver` - chrome driver
  * `firefoxdriver` - firefox driver
* `/Screenshots` - Takes a screenshot in case of failures
* `/src/test/java/pageObject` - Contains page object for all the pages
  * `HomePage` - Contains common elements and methods of Amazon home page
  * `SearchPage` - Contains common elements and methods of Amazon Search page
* `/src/test/java/testCases`
  * `BaseClass` - Contains driver initilisation, teardown method and screenshot method 
  * `TC_SearchItem_Amazon_001` - Contains all the test cases of Search item and add to cart of best selling items
* `/src/test/java/testData`
  * `SearchData.xlsx` - Contains all the test data needed for the Test
* `/src/test/java/testData`
  * `ReadConfig` - To fetch all the config from config.properties file
  * `Reporting` - Contains TestListener methods
  * `XLUtils` - Contains methods to read data from excel
* `/src/test/java/resources`
  * `extent-config.xml` - xml config for extent report
  * `log4j2.xml` - xml config of log4j for loggin config
  * `TestNG.xml` - xml config of TestNG for passing browser and setting other params
* `test-output` - contains all the html reports generated
* `pom.xml` - xml config for getting all the required dependencies


#How to Execute
 * Import the project to any of the IDE
 * Navigate to /src/test/java/resources/TestNG.xml right click and RunAs TestNG. This will execute the script and generate report
