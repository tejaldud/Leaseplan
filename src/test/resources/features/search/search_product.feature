Feature: Search for the products

### Please use endpoint GET https://waarkoop-server.herokuapp.com/api/v1/search/test/{product} for getting the products.
### Available products: "apple", "mango", "tofu", "water"
### Prepare Positive and negative scenarios

  Scenario Outline:Search for the product and validate the search results
    When User search for <keyword>
    Then he sees the results displayed for "<keyword>"
    Examples:
      | keyword |
      | apple   |
      | mango   |
      | tofu    |
      | water   |

  Scenario Outline:Search for the invalid product and validate error message
    When User search for <keyword>
    Then user should get an error message as "Not found" and does not see search results
    Examples:
      | keyword |
      | car     |
      | bike    |


