package com.bcc.cca.services.calculator;

import com.bcc.cca.entites.MarketCarItem;
import org.springframework.stereotype.Component;

@Component
public class MarketCarItemCalculator {
    public double multiplyByQuantity(MarketCarItem marketCarItem){
        return marketCarItem.getPrice() * marketCarItem.getQuantity();
    }
}
