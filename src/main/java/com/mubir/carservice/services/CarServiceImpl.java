package com.mubir.carservice.services;

import com.mubir.carservice.domain.Car;
import com.mubir.carservice.repositories.CarRepository;
import com.mubir.carservice.web.controller.NotFoundException;
import com.mubir.carservice.web.mapper.CarMapper;
import com.mubir.carservice.web.model.CarDto;
import com.mubir.carservice.web.model.CarModelEnum;
import com.mubir.carservice.web.model.CarPagedList;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    public final CarRepository carRepository;
    public final CarMapper carMapper;

    @Override
    public CarDto getById(UUID carId,Boolean inventoryInHand) {
        if(inventoryInHand)
        {return carMapper.carDtoWithInventory(carRepository.findById(carId).orElseThrow(NotFoundException::new));}else {
            return carMapper.carToCarDto(carRepository.findById(carId).orElseThrow(NotFoundException::new));
        }
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

    @Override
    public CarPagedList listCars(String carName, CarModelEnum carModelEnum,
                                 PageRequest pageRequest,Boolean inventoryInHand) {
        CarPagedList carPagedList;
        Page<Car> carPage;
        if (!StringUtils.isEmpty(carModelEnum) && !StringUtils.isEmpty(carName)) {
            carPage = carRepository.findAllByCarNameAndCarModel(carName, carModelEnum, pageRequest);
        } else if (StringUtils.isEmpty(carModelEnum) && !StringUtils.isEmpty(carName)) {
            carPage = carRepository.findAllByCarModel(carModelEnum, pageRequest);
        } else if (!StringUtils.isEmpty(carModelEnum) && StringUtils.isEmpty(carName)) {
            carPage = carRepository.findAllByCarName(carName, pageRequest);
        } else {
            carPage = carRepository.findAll(pageRequest);
        }
        if(inventoryInHand) {
            carPagedList = new CarPagedList(carPage.getContent()
                    .stream().map(carMapper::carDtoWithInventory).collect(Collectors.toList()),
                    PageRequest.of(carPage.getPageable().getPageNumber(), carPage.getPageable().getPageSize()),
                    carPage.getTotalElements());
        }else
        {
            carPagedList = new CarPagedList(carPage.getContent()
                    .stream().map(carMapper::carToCarDto).collect(Collectors.toList()),
                    PageRequest.of(carPage.getPageable().getPageNumber(), carPage.getPageable().getPageSize()),
                    carPage.getTotalElements());
        }

        return carPagedList;
    }
}
