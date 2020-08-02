package com.mubir.carservice.services;

import com.mubir.carservice.domain.Car;
import com.mubir.carservice.repositories.CarRepository;
import com.mubir.carservice.web.controller.NotFoundException;
import com.mubir.carservice.web.mapper.CarMapper;
import com.mubir.carservice.web.model.CarDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService{
    public final CarRepository carRepository;
    public final CarMapper carMapper;

    @Override
    public CarDto getById(UUID carId) {
        return carMapper.carToCarDto(carRepository.findById(carId).orElseThrow(NotFoundException::new));
    }

    @Override
    public CarDto saveNewCar(CarDto carDto) {
        return carMapper.carToCarDto(carRepository.save(carMapper.carDtoToCar(carDto)));
    }

    @Override
    public CarDto updateCar(UUID carId, CarDto carDto) {
        Car car = carRepository.findById(carId).orElseThrow(NotFoundException::new);

        car.setCarName(carDto.getCarName());
        car.setCarModel(carDto.getCarModel().name());
        car.setPrice(carDto.getPrice());
        car.setUpc(carDto.getUpc());
        return carMapper.carToCarDto(carRepository.save(car));
    }
}
