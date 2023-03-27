package com.alanjsantos.orderapi.controller;

import com.alanjsantos.orderapi.models.dto.CreateProductDTO;
import com.alanjsantos.orderapi.models.dto.UpdateProductDTO;
import com.alanjsantos.orderapi.models.entity.Product;
import com.alanjsantos.orderapi.models.dto.ResponseProductDTO;
import com.alanjsantos.orderapi.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ModelMapper modelMapper;
    @PostMapping
    public ResponseEntity<ResponseProductDTO> create(@Valid @RequestBody CreateProductDTO createProductDTO) {
        var product = productService.create(createProductDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(product, ResponseProductDTO.class));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseProductDTO> findById(@PathVariable Long id) {
        var product = productService.findById(id);
        var productDTO = modelMapper.map(product, ResponseProductDTO.class);
        return ResponseEntity.ok(productDTO);
    }

    @GetMapping
    public ResponseEntity<List<ResponseProductDTO>> findAll() {
        var products = productService.findAll();
        var productDTOs = products.stream()
                .map(product -> modelMapper.map(product, ResponseProductDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(productDTOs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseProductDTO> update(@PathVariable Long id, @RequestBody UpdateProductDTO updateProductDTO) {
        var product = productService.update(updateProductDTO, id);

        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(product, ResponseProductDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
