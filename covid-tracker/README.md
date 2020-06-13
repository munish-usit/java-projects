# COVID-19 dashboard

# Functions 

##### Problem context: 
 A user can register an account
 A user can login
 An authenticated user can view a COVID-19 dashboard for India. 
 The  dashboard can include anything you feel is useful - eg total cases,  active cases, deaths, graphs etc. 
 
	
##### Solution steps:
	1.	Build login/signup authentication system.
	2. 	Create a webservice that will fetch latest covid 19 stats.
	3. 	Create UI and itegrate the required data.


##### Frameworks:
	1.	Spring Boot 2.3.0 RELEASE
	2. 	MySql 5.7
	3. 	Thynmleaf as Template Engine


##### Java Version: 1.8


##### Unit Testcases
* 	Junit test cases is used with maven build.
* 	JUnit is written using Mockito to mock MYSql Repository.


##### Command to Run the application
* 	mvn -fn clean install
*   java -jar covid-tracker-1.0.jar


 