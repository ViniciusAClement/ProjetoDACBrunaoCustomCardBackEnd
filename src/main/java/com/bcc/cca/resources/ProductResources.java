package com.bcc.cca.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bcc.cca.entites.Product;
import com.bcc.cca.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductResources extends GenericResource<Product, Long>{
	@Autowired
    public ProductResources (ProductService service) {
        super(service);
    }
}
