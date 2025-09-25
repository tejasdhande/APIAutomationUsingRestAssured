Feature: Validating Place APIs

@AddPlace @Regression
Scenario Outline: Verify if place being successfully added using AddPlaceAPI

Given Add Place Payload with "<name>" "<language>" "<address>"
When User calls "AddPlaceAPI" with "Post" http request
Then The API call is success with status code 200
And "status" in response body is "OK"
And "scope" in response body is "APP"
And Verify place_id created maps to "<name>" using "GetPlaceAPI"

Examples:
	|name|language|address|
	|Tejas|English|West Kurla|
	#|Kajal|Marathi|Pune|

@DeletePlace @Regression
Scenario: Verify if DeletePlace functionality works
Given Delete Place payload
When User calls "DeletePlaceAPI" with "Post" http request
Then The API call is success with status code 200
And "status" in response body is "OK"



