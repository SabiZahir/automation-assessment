
#Author: Sabi
#Date:  June 10 2022
Feature: Test Users Restful API on Mockaroo

  Scenario: Test Post Request on Users API on Mockaroo service
    Given user sets the header content type and accept that as "application/json"
    When user makes a post request with a user payload and verifies the status code and id
    And user makes a get request with a user id
    Then user should get valid status code and payload