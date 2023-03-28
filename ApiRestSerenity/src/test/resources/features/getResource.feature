Feature: Obtain Resources from ReqRes API
  As a user of ReqRes API
  I want to get a list of resources by resource type
  So that I can view the details of all resources of a specific type

  @GetResource
  Scenario Outline: Obtain resources from the ReqRes API
    Given the ReqRes API is available
    When I make a GET request to retrieve the <resource> list
    Then the response status code should be <code>
    And the response body should contain a list of <resource>
    Examples:
      | resource   | code |
      | "users"    | 200  |
      | "unknown"  | 200  |
      | "products" | 200  |
      | "posts"    | 200  |
      | ""         | 404  |
