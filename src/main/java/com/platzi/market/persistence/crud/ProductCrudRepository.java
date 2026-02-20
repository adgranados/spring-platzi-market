package com.platzi.market.persistence.crud;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.platzi.market.persistence.entity.Producto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ProductCrudRepository extends CrudRepository<Producto, Integer> {

    List<Producto> findByIdCategoria(int idCategoria);

    List<Producto> findByIdCategoriaOrderByNombreAsc(int idCategoria);

    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidadStock, boolean estado);

    Optional<Producto> findByCodigoBarras(String codigoBarras);

    // productos que no han sido vendidos en los ultimos 10 dias
    @Query("SELECT p FROM Producto p WHERE NOT EXISTS (SELECT 1 FROM ComprasProducto cp JOIN cp.compra c WHERE cp.producto = p AND c.fecha > :fecha)")
    Optional<List<Producto>> findByFechaGreaterThan(@Param("fecha") LocalDateTime fecha);
}
