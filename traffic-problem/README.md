# GeektrustChallenges
Challenges on Geektrust with readable, scalable and optimized code.

# Challenge 1: Lengaburu Traffic

##### Problem context: 
Our problem is set in the traffic snarls of planet Lengaburu. After the recent Falicornian war, victorious King Shan of Lengaburu wishes to tour his kingdom. But the traffic in Lengaburu is killing. You should see how Silk Dorb gets jammed in the evening!

King Shan now would like to visit Hallitharam and RK Puram on the same day. Write code to determine which orbits
& vehicle he should take to visit both destinations in the quickest possible time.

##### Analysis:
King wants to visit from one residential area to another residential area.
 *	These two different places are connected via different orbits/routes. Each routes have defined distance and craters to go through.
 *	King can choose any vehicle. Each vehicle has it's own configuration. E.g. Speed, time to cross a crater, suitable weathers.
 *	Weather of Lengaburu, affects the number of craters in an orbits. It can changes (reduce/increase) craters. So weather has an impact on orbits/routes as well.
	
##### Solution steps:
	1.	Get weather by weather-type.
	2. 	Based on weather type get all suitable vehicle names and get their corresponding Vehicle objects.
	3. 	Get all available orbit-sequences to traverse single/multiple destinations and update them with user inputs (i.e. speed limit)
	4.	Now, based on weather type we can identify actual number of craters for each available orbit.
	5.	Find out optimum traverse time for each orbit/route - sequence and vehicle combination.
	6.	Compare these times and find out the optimized one.

##### Java Version: 1.8

##### Objects:

 *  Orbit[source: String; destination: String; number of craters: Integer; distance (megamile): Integer; speed limit: Velocity]
 *  Weather[weather type: enum; list of possible vehicle names: List<String>; change rate (+/- XX%): Integer;]
 *  Vehicle[name: String; speed: Velocity; time to cross a cracker(minutes): Integer]
 * 	Velocity[speed: Integer; unit: String (megamiles/hour)]
 *  TraverseDetail[time (minutes): Integer; sequence of orbits: List<Orbit>, vehicle: Vehicle] 
 
##### Assumption: 

 *	For any orbit source and destination, can't be interchanged. As road could be two ways. 
 *  Unit of speed limit of an orbit and vechicle's speed should be same. Default unit is megamiles/hour.

##### Note: 

 *	In this application, **GlobalMemory** is responsible to provide all possible weathers, orbits and vehicles. In real time application, these values will be retrieved from database or file system or through any third party web service.
 
##### Logs Configurations
* 	Application is using Apache log4j library to print logs to the file and the console.
* 	log4j2.json is located at location src\main\resources\log4j2.json  
* 	log4j2.json-test is located at location src\test\resources\log4j2-test.json  
*	log4j2-test.json is used to print logs related to unit test cases.
*   Configure LOG LEVEL = INFO or accordingly.
*	Configure Appender = RollingFile-Appender or Console or Both

##### Unit Testcases
* 	Junit test cases is used with maven build.
* 	FileReadTest case may failed depending on the input file location. 
*   To avoid such scenarios, I am building the application and ignoring the test failures


##### Command to Run the application
* 	mvn -fn clean install
*   java -jar geektrust.jar input.txt
* 	Output can be generate on the console or the log files depending on the log4j configurations.

 