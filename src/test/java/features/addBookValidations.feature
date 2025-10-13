Feature: Validating Library APIs

  Scenario Outline: Verify if book is being successfully added using AddBookAPI
    Given Add Book payload with "<name>","<isbn>","<aisle>" and "<author>"
    When User calls  AddBookAPI with Post http request
    Then The API call is success with the status code 200
    And Response body message should be "successfully added"

    Examples: 
      | Java with Selenium   | abc  |  123 | John  |
      | Python with Selenium | abcd | 1234 | Johny |
