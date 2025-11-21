package com.bcc.cca.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bcc.cca.entites.MarketCarItem;
import com.bcc.cca.services.MarketCarItemService;

@RestController
@RequestMapping("/marketcaritens")
public class MarketCarItemResources extends GenericResource<MarketCarItem, Long>{
	@Autowired
    public MarketCarItemResources (MarketCarItemService service) {
        super(service);
    }
}
