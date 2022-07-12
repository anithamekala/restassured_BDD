Feature: Get pet by ID

	@smokeTest
  Scenario: findByID
   Given headers
  	| accept 	        | application/json |
  	| Content-Type 	  | application/json |
  When submit get findByID
  Then get response contains
		| statusCode	| 200							|
		| statusLine  | HTTP/1.1 200 OK	|
		| status			|available				|