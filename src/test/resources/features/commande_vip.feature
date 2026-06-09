Feature: Order for a VIP customer

  Scenario: Order accepted with a 20 percent discount
    Given a product with reference "P003", name "Screen", unit price 250.00 euros and stock 5
    And a customer with profile "VIP"
    When the customer "vip@shop.com" orders 2 unit(s) of product "P003"
    Then the order is accepted
    And the receipt contains product reference "P003"
    And the receipt contains quantity 2
    And the total amount is 400.00 euros
    And the confirmation message contains "vip@shop.com"