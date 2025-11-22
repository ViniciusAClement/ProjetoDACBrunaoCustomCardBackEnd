package com.bcc.cca.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bcc.cca.entites.MarketCar;
import com.bcc.cca.entites.MarketCarItem;
import com.bcc.cca.entites.Product;
import com.bcc.cca.repositories.MarketCarItemRepository;
import com.bcc.cca.repositories.MarketCarRepository;
import com.bcc.cca.repositories.ProductRepository;

import jakarta.annotation.PostConstruct;

@Service
public class MarketCarItemService extends GenericService<MarketCarItem, Long>{

	@Autowired
	private MarketCarItemRepository repository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private MarketCarRepository marketCarRepository;
	
	@PostConstruct
    private void initRepository() {
        super.repository = repository;
    }
	
	@Override
	@Transactional
	public MarketCarItem create(MarketCarItem obj) {
		if (obj.getProduct() != null && obj.getProduct().getId() != null) {
			Product product = productRepository.findById(obj.getProduct().getId())
				.orElseThrow(() -> new jakarta.persistence.EntityNotFoundException("Product não encontrado com ID: " + obj.getProduct().getId()));
			obj.setProduct(product);
		}
		
		if (obj.getMarketcar() != null && obj.getMarketcar().getId() != null) {
			MarketCar marketCar = marketCarRepository.findById(obj.getMarketcar().getId())
				.orElseThrow(() -> new jakarta.persistence.EntityNotFoundException("MarketCar não encontrado com ID: " + obj.getMarketcar().getId()));
			obj.setMarketcar(marketCar);
		}
		
		if (obj.getPrice() == null && obj.getProduct() != null) {
			obj.setPrice(obj.getProduct().getPrice());
		}
		
		return repository.save(obj);
	}

    @Override
    protected void updateData(MarketCarItem entity, MarketCarItem newObj) {
        if (newObj.getProduct() != null && newObj.getProduct().getId() != null) {
            Product currentProduct = entity.getProduct();
            Product newProduct = productRepository.findById(newObj.getProduct().getId())
            	.orElseThrow(() -> new jakarta.persistence.EntityNotFoundException("Product não encontrado com ID: " + newObj.getProduct().getId()));
            
            if (currentProduct == null || !currentProduct.getId().equals(newProduct.getId())) {
                entity.setProduct(newProduct);
            }
        }
        
        entity.setQuantity(newObj.getQuantity());
        
        if (newObj.getPrice() != null) {
            entity.setPrice(newObj.getPrice());
        } else if (entity.getProduct() != null) {
            entity.setPrice(entity.getProduct().getPrice());
        }
    }
}
