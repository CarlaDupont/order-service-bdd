Feature: Reject an order when stock is insufficient

  Scenario: The requested quantity exceeds available stock
    Given a product with reference "P004", name "Headset", unit price 80.00 euros and stock 2
    And a customer with profile "STANDARD"
    When the customer "customer@shop.com" orders 3 unit(s) of product "P004"
    Then the order is rejected
    And the error message contains "Insufficient stock"