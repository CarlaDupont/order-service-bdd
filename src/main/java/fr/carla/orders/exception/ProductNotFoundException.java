package fr.carla.orders.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String reference) {
        super("Unknown product: " + reference);
    }
}