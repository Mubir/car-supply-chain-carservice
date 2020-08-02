package com.mubir.carservice.web.controller;

import com.mubir.carservice.services.CarService;
import com.mubir.carservice.web.model.CarDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/car")
public class CarController {
    private final CarService carService;
    @GetMapping("/{carId}")
    public ResponseEntity<CarDto> getCarById(@PathVariable("carId") UUID carId){
        return new ResponseEntity<>(carService.getById(carId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createCar(@Validated @RequestBody CarDto carDto)
    {
        return new ResponseEntity(carService.saveNewCar(carDto),HttpStatus.CREATED);
    }

    @PutMapping("/{carId}")
    public ResponseEntity updateCar(@PathVariable("carId") UUID carId,@Validated @RequestBody CarDto carDto)
    {
        return new ResponseEntity(carService.updateCar(carId,carDto),HttpStatus.NO_CONTENT);
    }
}
