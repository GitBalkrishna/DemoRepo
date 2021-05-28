
@capsule
Feature: Validation of Capsules
 
  @GetCapsules
  Scenario: Validation of Retrieving all the Capsules
    Given  Get Capsule API
    Given  User Calls "getCapsuleAPI" with "GET" http request
    Then   API call got Success with Status Code 200
    
  
  #@Create
  #Scenario: Validation of Capsule Creation
    #Given Create Capsule Payload
    #When User Calls "createCapsuleAPI" with "POST" http request
    #Then API call got Success with Status Code 200
#	  And "status" in response body is "OK" 
