package com.bcc.cca.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bcc.cca.dto.request.CarBrandRequestDTO;
import com.bcc.cca.dto.response.CarBrandResponseDTO;
import com.bcc.cca.entites.CarBrand;
import com.bcc.cca.mapper.CarBrandMapper;
import com.bcc.cca.services.CarBrandService;

@RestController
@RequestMapping("/carbrands")
public class CarBrandResources extends GenericResourceDTO<CarBrand, Long, CarBrandRequestDTO, CarBrandResponseDTO> {
	@Autowired
    public CarBrandResources(CarBrandService service, CarBrandMapper mapper) {
        super(service, mapper);
    }
}
