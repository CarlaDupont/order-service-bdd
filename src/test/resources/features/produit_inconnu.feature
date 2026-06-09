Feature: Reject an order for an unknown product

  Scenario: The requested product does not exist
    Given the product with reference "P999" is unknown
    And a customer with profile "STANDARD"
    When the customer "customer@shop.com" orders 1 unit(s) of product "P999"
    Then the order is rejected
    And the error message contains "Unknown product"