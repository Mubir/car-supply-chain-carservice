package com.mubir.carservice.web.mapper;

import com.mubir.carservice.domain.Car;
import com.mubir.carservice.services.inventory.CarInventoryService;
import com.mubir.carservice.web.model.CarDto;
import org.springframework.beans.factory.annotation.Autowired;

public class CarMapperDecorator implements CarMapper{
    private CarInventoryService carInventoryService;
    private CarMapper carMapper;
    @Autowired
    public void setCarInventoryService(CarInventoryService carInventoryService) {
        this.carInventoryService = carInventoryService;
    }
    @Autowired
    public void setCarMapper(CarMapper carMapper) {
        this.carMapper = carMapper;
    }

    @Override
    public CarDto carToCarDto(Car car) {
        CarDto dto = carMapper.carToCarDto(car);
        dto.setQuantityOnHand(carInventoryService.getOnhandInventory(car.getId()));
        return dto;
    }

    @Override
    public Car carDtoToCar(CarDto carDto) {
        return carMapper.carDtoToCar(carDto);
    }
}
