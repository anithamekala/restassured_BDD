Feature: Post Pet

	@smokeTest
Scenario:  Add new pet
    Given headers
  	| accept 	        | application/json |
  	| Content-Type 	  | application/json |
    When submit post request
    | petId					| 0         |
    | petName 			| Milo      |
    | categoryId		| 0					|
    | categoryName 	| dog				|
    | tagId	  			| 0					|
    | tagName	  		| test			|
    | status				| available	|
    Then response contains
		| statusCode	| 200							|
		| statusLine  | HTTP/1.1 200 OK	|
		| status			|available				|
