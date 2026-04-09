package com.api.retrofit_api.controller;

import com.api.retrofit_api.dto.ProductDTO;
import com.api.retrofit_api.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/product")
public class ProductController {
    private List<Product> products = new ArrayList<>(Arrays.asList(
        new Product(1, "Azucar", 1500),
        new Product(2, "Yerba Mate", 4800),
        new Product(3, "Dulce de leche", 3600)

    ));

    @GetMapping
    public ResponseEntity<List<Product>> getAll(){
        return ResponseEntity.ok(products);
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> getOne(@PathVariable("id") int id){
        Product product = findById(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody ProductDTO productoDTO){
        int index = products.isEmpty()? 1 : getLastIndex() + 1;
        Product product = Product.builder()
                .id(index)
                .name(productoDTO.getName())
                .price(productoDTO.getPrice())
                .build();
        products.add(product);
        return ResponseEntity.ok(product);
    }

    @PutMapping("{id}")
    public ResponseEntity<Product> update(@PathVariable("id") int id , @RequestBody ProductDTO productoDTO){
        Product product = findById(id);
        product.setName(productoDTO.getName());
        product.setPrice(productoDTO.getPrice());
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Product> update(@PathVariable("id") int id){
        Product product = findById(id);
        products.remove(product);
        return ResponseEntity.ok(product);
    }

    private int getLastIndex() {
        return products.stream().max(Comparator.comparing(Product::getId)).get().getId();
    }


    private Product findById(int id) {
        return products.stream().filter(p -> p.getId() == id).findAny().orElse(null);
    }
}
