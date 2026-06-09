package fr.carla.orders.repository;

import fr.carla.orders.domain.Product;

import java.util.Optional;

public interface ProductRepository {

    Optional<Product> findByReference(String reference);
}