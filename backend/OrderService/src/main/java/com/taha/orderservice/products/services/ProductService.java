package com.taha.orderservice.products.services;

import com.taha.orderservice.products.interfaces.ProductRepository;
import com.taha.orderservice.products.model.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    public Optional<Product> getProduct(String id) {
        log.info("Getting product with id: {}", id);
        return productRepository.findById(id);
    }
    public Optional<Page<Product>> getProducts(int page, int size) {
        log.info("Getting products with page: {} and size: {}", page, size);
        return Optional.of(productRepository.findAll(PageRequest.of(page, size)));
    }
    public void saveProduct(Product product) {
        log.info("Saving product: {}", product);
        productRepository.save(product);
    }
    public void deleteProduct(String id) {
        log.info("Deleting product with id: {}", id);
        productRepository.deleteById(id);
    }
    public void saveProducts(List<Product> products){
        log.info("Saving products: {}", products);
        productRepository.saveAll(products);
    }
}
