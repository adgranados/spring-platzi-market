package com.platzi.market.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.platzi.market.domain.entity.Purchase;
import com.platzi.market.domain.repository.PurchaseRepository;
import com.platzi.market.persistence.crud.CompraCrudRepository;
import com.platzi.market.persistence.entity.Compra;
import com.platzi.market.persistence.mapper.PurchaseMapper;

public class CompraRepository implements PurchaseRepository {

    @Autowired
    private CompraCrudRepository compraCrudRepository;

    @Autowired
    private PurchaseMapper mapper;

    @Override
    public List<Purchase> getAll() {
        List<Compra> compras = (List<Compra>) this.compraCrudRepository.findAll();
        return mapper.toPurchases(compras);
    }

    @Override
    public Optional<List<Purchase>> getByClient(String clientId) {
        Optional<List<Compra>> compras = this.compraCrudRepository.findByIdCliente(clientId);
        if (compras.isPresent()) {
            return Optional.of(mapper.toPurchases(compras.get()));
        }
        return Optional.empty();
    }

    @Override
    public Purchase save(Purchase purchase) {
        final Compra compra = mapper.toCompra(purchase);
        compra.getProductos().forEach(producto -> producto.setCompra(compra));
        Compra savedCompra = this.compraCrudRepository.save(compra);
        return mapper.toPurchase(savedCompra);
    }

}
