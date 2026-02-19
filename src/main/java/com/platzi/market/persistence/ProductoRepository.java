package com.platzi.market.persistence;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

import com.platzi.market.persistence.crud.ProductCrudRepository;
import com.platzi.market.persistence.entities.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductoRepository {

    private final ProductCrudRepository productCrudRepository;

    public ProductoRepository(ProductCrudRepository productCrudRepository) {
        this.productCrudRepository = productCrudRepository;
    }

    public List<Producto> getAll() {
        return (List<Producto>) productCrudRepository.findAll();
    }

    public List<Producto> findByIdCategoria(int idCategoria) {
        return productCrudRepository.findByIdCategoriaOrderByNombreAsc(idCategoria);
    }

    public Optional<List<Producto>> getProductosBajoStock(int cantidadStock) {
        return productCrudRepository.findByCantidadStockLessThanAndEstado(cantidadStock, true);
    }

    public Optional<Producto> getProducto(int idProducto) {
        return productCrudRepository.findById(idProducto);
    }

    public Optional<Producto> getProducto(String codigoBarras) {
        return productCrudRepository.findByCodigoBarras(codigoBarras);
    }

    public Producto save(Producto producto) {
        return productCrudRepository.save(producto);
    }

    public void delete(int idProducto) {
        productCrudRepository.deleteById(idProducto);
    }

    public Optional<List<Producto>> getProductosNoVendidosEn10Dias() {
        return productCrudRepository.findByFechaGreaterThan(LocalDateTime.now().minusDays(10));
    }
}
