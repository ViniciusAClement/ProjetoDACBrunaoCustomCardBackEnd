package com.bcc.cca.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bcc.cca.entites.CardInfo;
import com.bcc.cca.services.CardInfoService;

@RestController
@RequestMapping("/cardinfos")
public class CardInfoResources extends GenericResource<CardInfo, Long>{
	
	@Autowired
    public CardInfoResources (CardInfoService service) {
        super(service);
    }
}
