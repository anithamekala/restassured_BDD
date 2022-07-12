Feature: Update pet

	@smokeTest
  Scenario: 
    Given headers
  	| accept 	        | application/json |
  	| Content-Type 	  | application/json |
    When submit put request
    | petName 	| categoryName 	  | 
    | Rocky   	| German Shepard 	|
    Then put response contains
		| statusCode	  | 200							|
		| statusLine    | HTTP/1.1 200 OK	|
		| status			  | available			  |
		| petName 		  | Rocky           |
    | categoryName  | German Shepard	|