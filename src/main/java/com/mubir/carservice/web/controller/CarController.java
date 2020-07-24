package com.mubir.carservice.web.controller;

import com.mubir.carservice.web.model.CarDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/car")
public class CarController {
    @GetMapping("/{carId}")
    public ResponseEntity<CarDto> getCarById(@PathVariable("carId") UUID carId){
        return new ResponseEntity<>(CarDto.builder().build(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createCar(@RequestBody CarDto car)
    {
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{carId}")
    public ResponseEntity updateCar(@PathVariable("carId") UUID carId,@RequestBody CarDto car)
    {
        return new ResponseEntity(HttpStatus.FOUND);
    }
}
