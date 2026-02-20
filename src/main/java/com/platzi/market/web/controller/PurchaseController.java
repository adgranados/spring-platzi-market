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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.platzi.market.domain.entity.Purchase;
import com.platzi.market.domain.service.PurchaseService;

@RestController
@RequestMapping("/purchases")
@Tag(name = "Purchases", description = "API for Purchase operations")
public class PurchaseController {

    private PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping("/")
    @Operation(summary = "Get all purchases", description = "Get a list of all purchases in the market")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity<List<Purchase>> getAll() {
        return ResponseEntity.ok(purchaseService.getAll());
    }

    @GetMapping("/{clientId}")
    @Operation(summary = "Get purchases by client ID", description = "Get a list of purchases made by a specific client")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity<List<Purchase>> getByClient(
            @Parameter(description = "The ID of the client", required = true, example = "4546221") @PathVariable("clientId") String clientId) {
        return ResponseEntity.ok(purchaseService.getByClient(clientId));
    }

    @PostMapping("/")
    @Operation(summary = "Save a new purchase", description = "Save a new purchase in the market")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "CREATED"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST")
    })
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
