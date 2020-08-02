package com.mubir.carservice.services;

import com.mubir.carservice.web.model.CarDto;

import java.util.UUID;

public interface CarService {
    CarDto getById(UUID carId);
    CarDto saveNewCar(CarDto carDto);
    CarDto updateCar(UUID carId,CarDto carDto);
}
