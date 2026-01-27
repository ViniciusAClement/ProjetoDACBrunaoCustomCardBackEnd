package com.bcc.cca.services.calculator;

import com.bcc.cca.Exceptions.ConflictException;
import com.bcc.cca.entites.MarketCarItem;
import org.springframework.stereotype.Component;

@Component
public class MarketCarItemCalculator {
    public double multiplyByQuantity(MarketCarItem marketCarItem){
        if (marketCarItem == null) {
            throw new ConflictException("Item do carrinho inválido");
        }

        if (marketCarItem.getQuantity() == null || marketCarItem.getQuantity() <= 0) {
            throw new ConflictException("Quantidade inválida");
        }

        if (marketCarItem.getProduct() == null || marketCarItem.getProduct().getPrice() == null) {
            throw new ConflictException("Produto inválido ou sem preço");
        }

        return marketCarItem.getProduct().getPrice() * marketCarItem.getQuantity();
    }
}
