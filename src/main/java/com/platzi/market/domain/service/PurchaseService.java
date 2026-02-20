package com.platzi.market.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.platzi.market.domain.entity.Purchase;
import com.platzi.market.domain.repository.PurchaseRepository;

@Service
public class PurchaseService {

    private PurchaseRepository purchaseRepository;

    public PurchaseService(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    public List<Purchase> getAll() {
        return purchaseRepository.getAll();
    }

    public List<Purchase> getByClient(String clientId) {
        Optional<List<Purchase>> purchases = purchaseRepository.getByClient(clientId);
        return purchases.orElse(new ArrayList<>());
    }

    public Purchase save(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }
}
