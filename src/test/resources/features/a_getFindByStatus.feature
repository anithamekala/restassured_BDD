Feature: Get findByStatus
  
  @smokeTest
  Scenario: Call findBystatus rest endpoint to get all available statuses
   	Given query param as
  	| status 	  | available |
	  When get findByStatus
	  And response for findByStatus contains
	  | statusCode	  | 200							|
		| statusLine    | HTTP/1.1 200 OK	|
		| status			  | available			  |
    	
  @smokeTest
  Scenario: Call findBystatus rest endpoint to get all pending statuses
	  Given query param as
	    | status 	  | pending |
	  When get findByStatus
	  And response for findByStatus contains
	  | statusCode	  | 200							|
		| statusLine    | HTTP/1.1 200 OK	|
	  | status 	      | pending         |
    	
  @smokeTest
  Scenario: Call findBystatus rest endpoint to get all sold statuses
  	Given query param as
  	  | status 	  | sold |
    When get findByStatus
    And response for findByStatus contains
    	| statusCode	  | 200							|
			| statusLine    | HTTP/1.1 200 OK	|
      | status 	      | sold            |  
 
 
