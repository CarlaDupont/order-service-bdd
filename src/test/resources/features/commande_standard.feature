Feature: Order for a STANDARD customer

  Scenario: Order accepted without discount
    Given a product with reference "P001", name "Keyboard", unit price 50.00 euros and stock 10
    And a customer with profile "STANDARD"
    When the customer "standard@shop.com" orders 2 unit(s) of product "P001"
    Then the order is accepted
    And the receipt contains product reference "P001"
    And the receipt contains quantity 2
    And the total amount is 999.00 euros
    And the confirmation message contains "standard@shop.com"