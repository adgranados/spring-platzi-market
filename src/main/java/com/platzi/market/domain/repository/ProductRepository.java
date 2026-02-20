package com.platzi.market.domain.repository;

import java.util.List;
import java.util.Optional;

import com.platzi.market.domain.entity.Product;

public interface ProductRepository {
    List<Product> getAll();

    Optional<List<Product>> getByCategoria(int idCategoria);

    Optional<Product> getProducto(int idProducto);

    Optional<Product> getProducto(String codigoBarras);

    Optional<List<Product>> getProductosBajoStock(int cantidadStock);

    Product save(Product product);

    void delete(int idProducto);

    Optional<List<Product>> getProductosNoVendidosEn10Dias();
}
