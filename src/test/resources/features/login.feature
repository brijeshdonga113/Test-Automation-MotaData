Feature: Login functionality
  As a registered user
  I want to log in to the application
  So that I can access my account dashboard

  Background:
    Given I am on the login page

  @Smoke @Login
  Scenario: Successful login with valid credentials
    When I enter a valid username and password
    And I click the login button
    Then I should be redirected to the dashboard
    And I should see a welcome message
