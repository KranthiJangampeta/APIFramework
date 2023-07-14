Feature: iTunes Store API- Constructing Searches

  @Test1
  Scenario: Search for content by providing the "term" parameter with valid text string
    Given construct a fully qualified URL content request with parameters
      | Key  | Value        |
      | term | jack+johnson |
    When user calls "SEARCH" resource with "GET" http request
    Then the API call is success with status code 200
    And "resultCount" in the response body is 50
    And "wrapperType" in the response body is "track" in result 1
    And "kind" in the response body is "feature-movie" in result 6

  @Test2
  Scenario: Search for content by providing the "country" parameter with valid two-letter country codes
    Given construct a fully qualified URL content request with parameters
      | Key     | Value     |
      | country | ca        |
      | term    | jim+jones |
    When user calls "SEARCH" resource with "GET" http request
    Then the API call is success with status code 200
    And "resultCount" in the response body is 50
    And "country" in the response body is "CAN" in result 2
    And "currency" in the response body is "CAD" in result 24

  @Test3
  Scenario: Search for content by specifying the "media" parameter with valid media types
    Given construct a fully qualified URL content request with parameters
      | Key   | Value   |
      | media | tvShow  |
      | term  | friends |
    When user calls "SEARCH" resource with "GET" http request
    Then the API call is success with status code 200
    And "resultCount" in the response body is 50
    And "kind" in the response body is "tv-episode" in result 18
    And "collectionName" in the response body is "Friends: The Complete Series" in result 18

  @Test4
  Scenario: Search for content by specifying the "entity" parameter with valid entities based on the media type
    Given construct a fully qualified URL content request with parameters
      | Key    | Value          |
      | entity | ebook          |
      | term   | RichDadPoorDad |
    When user calls "SEARCH" resource with "GET" http request
    Then the API call is success with status code 200
    And "resultCount" in the response body is 50
    And "artistName" in the response body is "Robert T. Kiyosaki" in result 1
    And "trackName" in the response body is "Rich Dad Poor Dad" in result 1
    And "kind" in the response body is "ebook" in result 1

  @Test5
  Scenario: Search for content by specifying the "attribute" parameter with valid attributes based on the media type
    Given construct a fully qualified URL content request with parameters
      | Key       | Value         |
      | attribute | allArtistTerm |
      | entity    | allArtist     |
      | term      | maroon        |
    When user calls "SEARCH" resource with "GET" http request
    Then the API call is success with status code 200
    And "resultCount" in the response body is 50
    And "artistName" in the response body is "Maroon 5" in result 1
    And "wrapperType" in the response body is "artist" in result 1
    And "primaryGenreName" in the response body is "Pop" in result 1

  @Test6
  Scenario: Search for content with a combination of multiple parameters
    Given construct a fully qualified URL content request with parameters
      | Key       | Value        |
      | term      | Interstellar |
      | country   | us           |
      | media     | movie        |
      | entity    | movie        |
      | attribute | movieTerm    |
    When user calls "SEARCH" resource with "GET" http request
    Then the API call is success with status code 200
    And "resultCount" in the response body is 1
    And "artistName" in the response body is "Christopher Nolan" in result 1
    And "collectionName" in the response body is "Sci-Fi 5-Movie Collection" in result 1
    And "trackName" in the response body is "Interstellar" in result 1
    And "country" in the response body is "USA" in result 1
    And "releaseDate" in the response body is "2014-11-07T08:00:00Z" in result 1

  @Test7
  Scenario: Search for content and verify the number of search results using the "limit" parameter.
    Given construct a fully qualified URL content request with parameters
      | Key   | Value    |
      | limit |       20 |
      | term  | software |
    When user calls "SEARCH" resource with "GET" http request
    Then the API call is success with status code 200
    And "resultCount" in the response body is 20