package com.mubir.carservice.services;

import com.mubir.carservice.web.model.CarDto;
import com.mubir.carservice.web.model.CarModelEnum;
import com.mubir.carservice.web.model.CarPagedList;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface CarService {
    CarDto getById(UUID carId,Boolean inventoryInHand);
    CarDto saveNewCar(CarDto carDto);
    CarDto updateCar(UUID carId,CarDto carDto);
    CarPagedList listCars(String carName, CarModelEnum carModelEnum,
                          PageRequest pageRequest,Boolean inventoryInHand);
    CarDto getByUpc(String upc);
}
