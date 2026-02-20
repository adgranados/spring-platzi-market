package com.platzi.market.web.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.platzi.market.domain.entity.Purchase;
import com.platzi.market.domain.service.PurchaseService;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {

    private PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Purchase>> getAll() {
        return ResponseEntity.ok(purchaseService.getAll());
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<List<Purchase>> getByClient(@PathVariable("clientId") String clientId) {
        return ResponseEntity.ok(purchaseService.getByClient(clientId));
    }

    @PostMapping("/")
    public ResponseEntity<Purchase> save(@RequestBody Purchase purchase) {
        Purchase savedPurchase = purchaseService.save(purchase);
        if (savedPurchase == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedPurchase);
    }

}
