Feature: Validating the RSA Place API's 

@AddPlace
Scenario Outline: Verify if place is being successfully added using AddPlaceAPI
	Given Add Place Payload with "<name>" "<phone_number>" "<address>" "<language>"
	When user calls "addPlaceAPI" with "Post" http request
	Then the API call got success with status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And Verify Place_ID created maps to "<name>" using "getPlaceAPI"
	
Examples:
		|name		|phone_number			|address				|language	|
		|AAHouse	|(+91) 989 989 3921)	|2/396 V.O.C Nagar		|Tamil		|
	#	|DAN House	|(+91) 852 963 7412)	|546 Bharathi Nagar		|Turkish	|
	
@DeletePlace
Scenario: Verify if place is being deleted successfully using DeletePlaceAPI
	Given Delete Place payload
	When user calls "deletePlaceAPI" with "Delete" http request
	Then the API call got success with status code 200
	And "status" in response body is "OK"