# DCForcast Project
Created by: Duvelis Carao

# Stack:
	- Java 8
	- maven
	- Spring Boot
	- Mockito

# Run and Testing: 
	- In order to run the application, use the maven comannd
		mvn spring-boot:run
	
		then access to the default URL:
		Example: http://localhost:8080/data/berlin
		
	- To run the unit testing, use the maven command
		mvn test


# API defined:
Example:
	http://localhost:8080/data/berlin

	For more detail, please check swagger file defined in the project:
	dcforecast/swaggerAPI.json
	
	or access to the running application:

		http://localhost:8080/swagger-ui.html#/
		http://localhost:8080/v2/api-docs


# Integration:
	- The ApiKey and endpoint for the Open Weather Map is defined in the 
		dcforecast/src/main/resources/application.properties	
	

# Extras:
	Added a cache to retrieve the values for an specific city
	
