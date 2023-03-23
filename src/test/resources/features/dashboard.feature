Feature: Dashboard Page
  @wip @db
  Scenario: Dashboard data verification
    Given the user logged in as "librarian"
    When user gets all information from modules
    Then the information should be same with database