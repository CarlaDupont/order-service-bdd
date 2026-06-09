Feature: Order for a PREMIUM customer

  Scenario: Order accepted with a 10 percent discount
    Given a product with reference "P002", name "Mouse", unit price 40.00 euros and stock 20
    And a customer with profile "PREMIUM"
    When the customer "premium@shop.com" orders 3 unit(s) of product "P002"
    Then the order is accepted
    And the receipt contains product reference "P002"
    And the receipt contains quantity 3
    And the total amount is 999.00 euros
    And the confirmation message contains "premium@shop.com"