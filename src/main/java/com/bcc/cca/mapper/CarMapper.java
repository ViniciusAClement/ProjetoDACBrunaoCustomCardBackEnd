package com.bcc.cca.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bcc.cca.dto.request.CarRequestDTO;
import com.bcc.cca.dto.response.CarResponseDTO;
import com.bcc.cca.entites.Car;

@Component
public class CarMapper implements GenericMapper<Car, CarRequestDTO, CarResponseDTO> {
    
    @Autowired
    private CarBrandMapper carBrandMapper;
    
    public Car toEntity(CarRequestDTO dto) {
        Car car = new Car();
        car.setId(dto.getId());
        car.setName(dto.getName());
        car.setYearOfCar(dto.getYearOfCar());
        return car;
    }
    
    public CarResponseDTO toResponseDTO(Car entity) {
        CarResponseDTO dto = new CarResponseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setYearOfCar(entity.getYearOfCar());
        
        if (entity.getCarBrand() != null) {
            dto.setCarBrand(carBrandMapper.toResponseDTO(entity.getCarBrand()));
        }
        
        return dto;
    }
    
    public void updateEntityFromDTO(Car entity, CarRequestDTO dto) {
        entity.setName(dto.getName());
        entity.setYearOfCar(dto.getYearOfCar());
    }
}

