package com.bcc.cca.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.bcc.cca.Exceptions.EntityNotFoundException;
import com.bcc.cca.entites.MarketCar;
import com.bcc.cca.entites.Product;
import com.bcc.cca.repositories.MarketCarRepository;
import com.bcc.cca.repositories.ProductRepository;
import com.bcc.cca.services.calculator.MarketCarItemCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bcc.cca.dto.request.MarketCarItemRequestDTO;
import com.bcc.cca.dto.response.MarketCarItemResponseDTO;
import com.bcc.cca.entites.MarketCarItem;
import com.bcc.cca.mapper.MarketCarItemMapper;
import com.bcc.cca.repositories.MarketCarItemRepository;

@Service
public class MarketCarItemService extends GenericServices<MarketCarItem,MarketCarItemRequestDTO,MarketCarItemResponseDTO,MarketCarItemMapper>{

    private final MarketCarItemRepository repository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MarketCarRepository marketCarRepository;
    @Autowired
    private MarketCarItemCalculator marketCarItemCalculator;

    public MarketCarItemService(MarketCarItemRepository repository, MarketCarItemMapper mapper) {
        super(mapper);
        this.repository = repository;
    }

    @Override
    protected MarketCarItemRepository getRepository() {
        return repository;
    }

    @Transactional
    @Override
    public MarketCarItemResponseDTO create(MarketCarItemRequestDTO dto){
        MarketCarItem marketCarItem = mapper.toEntity(dto);
        MarketCar marketCar = marketCarRepository.findById(dto.getMarketCarId()).orElseThrow(() -> new EntityNotFoundException("Carrinho Inexistente"));;
        Product product = productRepository.findById(dto.getProductId()).orElseThrow(() -> new EntityNotFoundException("Produto Inexistente"));

        marketCarItem.setMarketcar(marketCarRepository.findById(dto.getMarketCarId()).orElseThrow(() -> new EntityNotFoundException("Carrinho Inexistente")));
        marketCarItem.setProduct(productRepository.findById(dto.getProductId()).orElseThrow(() -> new EntityNotFoundException("Produto Inexistente")));

        product.addMarketCarItem(marketCarItem);
        marketCar.addItem(marketCarItem);

        marketCarRepository.save(marketCar);
        productRepository.save(product);

        marketCarItem.setPrice(marketCarItemCalculator.multiplyByQuantity(marketCarItem));

        repository.save(marketCarItem);
        return mapper.toResponseDTO(marketCarItem);
    }
}