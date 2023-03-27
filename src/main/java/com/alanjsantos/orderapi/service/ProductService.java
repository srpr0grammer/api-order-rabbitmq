package com.alanjsantos.orderapi.service;

import com.alanjsantos.orderapi.models.dto.CreateProductDTO;
import com.alanjsantos.orderapi.models.dto.UpdateProductDTO;
import com.alanjsantos.orderapi.models.entity.Product;
import com.alanjsantos.orderapi.repository.ProductRepository;
import com.alanjsantos.orderapi.service.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    public Product create(CreateProductDTO createProductDTO) {
        var product = new Product();
        product.setName(createProductDTO.getName());
        product.setDescription(createProductDTO.getDescription());
        product.setPrice(createProductDTO.getPrice());
        return productRepository.save(product);
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Product not found."));
    }


    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product update(UpdateProductDTO productUp, Long id) {
        var product = findById(id);
        product.setName(productUp.getName());
        product.setDescription(productUp.getDescription());
        product.setPrice(productUp.getPrice());

        return productRepository.save(product);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
