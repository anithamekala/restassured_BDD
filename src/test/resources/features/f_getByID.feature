Feature: Get pet by ID

	@smokeTest
  Scenario: findByID
   Given headers
  	| accept 	        | application/json |
  	| Content-Type 	  | application/json |
  When submit get findByID
  Then get response after delete contains
		| statusCode	  | 404							        |
		| statusLine    | HTTP/1.1 404 Not Found 	|
		| code          | 1                       |
		| type          | error                   | 
		| message       | Pet not found           |