
Feature: Handling Google maps API
 
  @AddPlace
  Scenario Outline: Add Place API
    Given Perapre payload with "<name>" "<language>" "<address>"
    When User Call "AddPlaceAPI" and with "POST"
    Then the API call got success with status code "200"
    And "status" in response is "OK"
    And "scope" in response body is "APP"
    And verify place_Id created maps to "<name>" using "GetPlaceAPI" and with "GET"
    #Given Delete Payload 
    #When Delete place using "DeletePlaceAPI" and with "POST" 
    #Then "status" in response is "OK"
    

    Examples: 
      | name  | language  | address  |
      | name1 | English   | Hyd1     |
     # | name2 | Spanish   | Hyd2     |
      #| name3 | French    | Hyd2     |
      
 @DeletePlace     
Scenario: Delete payload
 Given Delete Payload 
 When Delete place using "DeletePlaceAPI" and with "POST" 
 Then "status" in response is "OK"