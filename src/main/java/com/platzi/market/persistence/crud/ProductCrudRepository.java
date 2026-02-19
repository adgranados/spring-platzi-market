package com.platzi.market.persistence.crud;

import com.platzi.market.persistence.entities.Producto;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ProductCrudRepository extends CrudRepository<Producto, Integer> {

    List<Producto> findByIdCategoria(int idCategoria);

    List<Producto> findByIdCategoriaOrderByNombreAsc(int idCategoria);

    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidadStock, boolean estado);

    Optional<Producto> findByCodigoBarras(String codigoBarras);

    // productos que no han sido vendidos en los ultimos 10 dias
    @Query("SELECT p FROM Producto p WHERE p.id NOT IN (SELECT cp.id.idProducto FROM ComprasProducto cp WHERE cp.id.fecha > :fecha)")
    Optional<List<Producto>> findByFechaGreaterThan(@Param("fecha") LocalDateTime fecha);
}
