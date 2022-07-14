Feature: Get findByStatus
@test
Scenario Outline: Call findBystatus rest endpoint to get all statuses
	  Given query param as "<status>"
	  When get findByStatus
	  And response contains "<statusCode>", "<statusLine>" and "<status>"
Examples: 
	  |statusCode	|statusLine       |status    |
	  |200        |HTTP/1.1 200 OK  |available |
	  |200				|HTTP/1.1 200 OK	|pending   |
	  |200				|HTTP/1.1 200 OK	|sold			 |