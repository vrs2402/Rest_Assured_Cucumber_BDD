Feature: Validating AddPlace API

  @Smoke @Regression
  Scenario Outline: Verifying Place is successfully added by AddPlace API
    Given User enters Add Place Payload with "<Name>","<Phone_Number>","<Address>","<Website>","<Language>"
    When User calls "AddPlaceAPI" with "Post" HTTP request
    Then the API call is success with Status Code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And Verify Place ID created maps to "<Name>" using "GetPlaceAPI" with "Get" HTTP request

    Examples: 
      | Name       | Phone_Number       | Address             | Website          | Language     |
      | Sydney SCG | (+91) 983 893 3937 | 70 winter walk, AUS | http://yahoo.com | English, USA |

  #	| Melboune MCG | (+91) 983 893 3739   | 70 Summer walk, AUS | http://Bing.com  | English, USA |
  @Sanity @Regression
  Scenario: Verifying Place is successfully deleted by DeletePlace API
    Given User enters Delete Place Payload
    When User calls "DeletePlaceAPI" with "Delete" HTTP request
    Then the API call is success with Status Code 200
    And "status" in response body is "OK"
