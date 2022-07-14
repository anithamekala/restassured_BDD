Feature: Get pet by ID

@smokeTest
Scenario: findByID
   Given headers
  	| accept 	        | application/json |
  	| Content-Type 	  | application/json |
  When submit delete by id
  Then delete response contains
		| statusCode	  | 200							|
		| statusLine    | HTTP/1.1 200 OK |
		| code          | 200             |
		| type          | unknown         |