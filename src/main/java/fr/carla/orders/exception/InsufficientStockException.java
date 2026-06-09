package fr.carla.orders.exception;

public class InsufficientStockException extends RuntimeException {

    public InsufficientStockException(
            int requestedQuantity,
            int availableStock
    ) {
        super(
                "Insufficient stock: requested quantity = "
                        + requestedQuantity
                        + ", available stock = "
                        + availableStock
        );
    }
}