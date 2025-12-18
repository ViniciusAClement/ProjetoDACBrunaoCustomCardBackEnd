package com.bcc.cca.services.calculator;

import com.bcc.cca.entites.MarketCar;
import com.bcc.cca.entites.MarketCarItem;
import org.springframework.stereotype.Component;

@Component
public class OrderCalculator {
    public double sumValue(MarketCar marketCar){
        Double valor = 0.0;

        for(MarketCarItem marketCarItem : marketCar.getMarketCarItens()){
            valor+=marketCarItem.getPrice();
        }
        return valor;
    }
}
