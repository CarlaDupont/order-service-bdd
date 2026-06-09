package fr.carla.orders.bdd;

import fr.carla.orders.domain.CustomerProfile;
import fr.carla.orders.domain.Order;
import fr.carla.orders.domain.OrderReceipt;
import fr.carla.orders.domain.Product;
import fr.carla.orders.repository.ProductRepository;
import fr.carla.orders.service.OrderService;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class OrderStepDefinitions {

    private ProductRepository productRepository;
    private OrderService orderService;

    private CustomerProfile customerProfile;
    private OrderReceipt orderReceipt;
    private RuntimeException caughtException;

    private String orderedProductReference;

    @Before
    public void setUp() {
        productRepository = mock(ProductRepository.class);
        orderService = new OrderService(productRepository);

        customerProfile = null;
        orderReceipt = null;
        caughtException = null;
        orderedProductReference = null;
    }

    @Given(
            "a product with reference {string}, name {string}, unit price {double} euros and stock {int}"
    )
    public void aProductExists(
            String reference,
            String name,
            double unitPrice,
            int stock
    ) {
        Product product = new Product(
                reference,
                name,
                BigDecimal.valueOf(unitPrice),
                stock
        );

        when(productRepository.findByReference(reference))
                .thenReturn(Optional.of(product));
    }

    @Given("the product with reference {string} is unknown")
    public void theProductIsUnknown(String reference) {
        when(productRepository.findByReference(reference))
                .thenReturn(Optional.empty());
    }

    @Given("a customer with profile {string}")
    public void aCustomerWithProfile(String profile) {
        customerProfile = CustomerProfile.valueOf(profile);
    }

    @When(
            "the customer {string} orders {int} unit\\(s) of product {string}"
    )
    public void theCustomerOrdersProduct(
            String email,
            int quantity,
            String productReference
    ) {
        orderedProductReference = productReference;

        Order order = new Order(
                email,
                productReference,
                quantity
        );

        try {
            orderReceipt = orderService.placeOrder(
                    order,
                    customerProfile
            );
        } catch (RuntimeException exception) {
            caughtException = exception;
        }
    }

    @Then("the order is accepted")
    public void theOrderIsAccepted() {
        assertNull(
                caughtException,
                "No exception should be thrown"
        );

        assertNotNull(
                orderReceipt,
                "A receipt should be returned"
        );

        verify(productRepository)
                .findByReference(orderedProductReference);
    }

    @Then("the order is rejected")
    public void theOrderIsRejected() {
        assertNotNull(
                caughtException,
                "An exception should be thrown"
        );

        assertNull(
                orderReceipt,
                "No receipt should be returned"
        );

        verify(productRepository)
                .findByReference(orderedProductReference);
    }

    @Then("the receipt contains product reference {string}")
    public void theReceiptContainsProductReference(
            String expectedReference
    ) {
        assertNotNull(orderReceipt);

        assertEquals(
                expectedReference,
                orderReceipt.getProductReference()
        );
    }

    @Then("the receipt contains quantity {int}")
    public void theReceiptContainsQuantity(
            int expectedQuantity
    ) {
        assertNotNull(orderReceipt);

        assertEquals(
                expectedQuantity,
                orderReceipt.getQuantity()
        );
    }

    @Then("the total amount is {double} euros")
    public void theTotalAmountIs(double expectedAmount) {
        assertNotNull(orderReceipt);

        BigDecimal expected = BigDecimal
                .valueOf(expectedAmount)
                .setScale(2);

        assertEquals(
                0,
                expected.compareTo(orderReceipt.getTotalAmount())
        );
    }

    @Then("the confirmation message contains {string}")
    public void theConfirmationMessageContains(
            String expectedText
    ) {
        assertNotNull(orderReceipt);

        assertTrue(
                orderReceipt
                        .getConfirmationMessage()
                        .contains(expectedText)
        );
    }

    @Then("the error message contains {string}")
    public void theErrorMessageContains(
            String expectedText
    ) {
        assertNotNull(caughtException);

        assertTrue(
                caughtException
                        .getMessage()
                        .contains(expectedText)
        );
    }
}