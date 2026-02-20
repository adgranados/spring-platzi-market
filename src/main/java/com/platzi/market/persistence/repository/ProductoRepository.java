package com.platzi.market.persistence.repository;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

import com.platzi.market.domain.entity.Product;
import com.platzi.market.domain.repository.ProductRepository;
import com.platzi.market.persistence.crud.ProductCrudRepository;
import com.platzi.market.persistence.entity.Producto;
import com.platzi.market.persistence.mapper.ProductMapper;

import org.springframework.stereotype.Repository;

@Repository
public class ProductoRepository implements ProductRepository {

    private final ProductCrudRepository productCrudRepository;
    private ProductMapper productMapper;

    public ProductoRepository(ProductCrudRepository productCrudRepository, ProductMapper productMapper) {
        this.productCrudRepository = productCrudRepository;
        this.productMapper = productMapper;
    }

    public List<Product> getAll() {
        List<Producto> productos = (List<Producto>) productCrudRepository.findAll();
        return productMapper.toProducts(productos);
    }

    public List<Producto> findByIdCategoria(int idCategoria) {
        return productCrudRepository.findByIdCategoriaOrderByNombreAsc(idCategoria);
    }

    public Optional<List<Product>> getProductosBajoStock(int cantidadStock) {
        Optional<List<Producto>> productos = productCrudRepository.findByCantidadStockLessThanAndEstado(cantidadStock,
                true);
        if (productos.isPresent()) {
            return Optional.of(productMapper.toProducts(productos.get()));
        }
        return Optional.empty();
    }

    public Optional<Product> getProducto(int idProducto) {
        Optional<Producto> producto = productCrudRepository.findById(idProducto);
        if (producto.isPresent()) {
            return Optional.of(productMapper.toProduct(producto.get()));
        }
        return Optional.empty();
    }

    public Optional<Product> getProducto(String codigoBarras) {
        Optional<Producto> producto = productCrudRepository.findByCodigoBarras(codigoBarras);
        if (producto.isPresent()) {
            return Optional.of(productMapper.toProduct(producto.get()));
        }
        return Optional.empty();
    }

    public Product save(Product product) {
        Producto producto = productMapper.toProducto(product);
        return productMapper.toProduct(productCrudRepository.save(producto));
    }

    public void delete(int idProducto) {
        productCrudRepository.deleteById(idProducto);
    }

    public Optional<List<Product>> getProductosNoVendidosEn10Dias() {
        Optional<List<Producto>> productos = productCrudRepository
                .findByFechaGreaterThan(LocalDateTime.now().minusDays(10));
        if (productos.isPresent()) {
            return Optional.of(productMapper.toProducts(productos.get()));
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<Product>> getByCategoria(int idCategoria) {
        List<Producto> productos = productCrudRepository.findByIdCategoriaOrderByNombreAsc(idCategoria);
        return Optional.of(productMapper.toProducts(productos));
    }
}
