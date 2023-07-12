Feature: Catalog Resources

  Scenario: Validate Catalog Resources API Response Code
    Given construct a search with parameter below keyValue pairs
      | Key   | Value        |
      | term  | jack+johnson |
      | limit |           25 |
    When user calls "SEARCH" resource with "GET" http request
    Then the API call is success with status code 200
    And "resultCount" in the response body is 25