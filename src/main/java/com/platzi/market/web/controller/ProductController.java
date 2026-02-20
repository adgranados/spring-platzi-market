package com.platzi.market.web.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.platzi.market.domain.entity.Product;
import com.platzi.market.domain.service.ProductService;

@RestController
@RequestMapping("/products")
@Tag(name = "Products", description = "API for Product operations")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    @Operation(summary = "Get all products", description = "Get a list of all products in the market")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity<List<Product>> getAll() {
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a product by ID", description = "Search a product with an ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    public ResponseEntity<Product> getProduct(
            @Parameter(description = "The id of the product", required = true, example = "7") @PathVariable("id") int idProducto) {
        return productService.getProducto(idProducto)
                .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/category/{id}")
    @Operation(summary = "Get products by category")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity<List<Product>> getByCategory(
            @Parameter(description = "The id of the category") @PathVariable("id") int idCategoria) {
        return productService.getByCategory(idCategoria)
                .map(products -> new ResponseEntity<>(products, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/")
    @Operation(summary = "Save a new product")
    @ApiResponse(responseCode = "201", description = "CREATED")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing product")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product,
            @Parameter(description = "The id of the product to update") @PathVariable("id") int idProducto) {
        product.setProductId(idProducto);
        return new ResponseEntity<>(productService.save(product), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a product by ID")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity<Boolean> deleteProduct(
            @Parameter(description = "The id of the product to delete") @PathVariable("id") int idProducto) {
        return new ResponseEntity<>(productService.delete(idProducto), HttpStatus.OK);
    }
}
