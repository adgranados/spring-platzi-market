package com.platzi.market.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.platzi.market.domain.Product;
import com.platzi.market.domain.repository.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAll() {
        return productRepository.getAll();
    }

    public Optional<Product> getProducto(int idProducto) {
        return productRepository.getProducto(idProducto);
    }

    public Optional<List<Product>> getByCategory(int idCategoria) {
        return productRepository.getByCategoria(idCategoria);
    }

    public Optional<List<Product>> getProductosBajoStock(int cantidadStock) {
        return productRepository.getProductosBajoStock(cantidadStock);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public boolean delete(int idProducto) {
        if (productRepository.getProducto(idProducto).isPresent()) {
            productRepository.delete(idProducto);
            return true;
        }
        return false;
    }

    public Optional<List<Product>> getProductosNoVendidosEn10Dias() {
        return productRepository.getProductosNoVendidosEn10Dias();
    }
}
