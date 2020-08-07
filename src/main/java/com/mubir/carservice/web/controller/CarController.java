package com.mubir.carservice.web.controller;

import com.mubir.carservice.services.CarService;
import com.mubir.carservice.web.model.CarDto;
import com.mubir.carservice.web.model.CarModelEnum;
import com.mubir.carservice.web.model.CarPagedList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
@RestController
public class CarController {
    private static final Integer DEFAULT_PAGE_NUMBER =0;
    private static final Integer DEFAULT_PAGE_SIZE = 25;
    private final CarService carService;
    @GetMapping("car/{carId}")
    public ResponseEntity<CarDto> getCarById(@PathVariable("carId") UUID carId,
                                             @RequestParam(value = "invetoryInHand",required = false) Boolean inventoryInHand){

        if(inventoryInHand == null)
        {
            inventoryInHand = false;
        }


            return new ResponseEntity<>(carService.getById(carId,inventoryInHand), HttpStatus.OK);

       // return new ResponseEntity<>(CarDto.builder().build(),HttpStatus.OK);
    }

    @PostMapping(path="car")
    public ResponseEntity createCar(@Validated @RequestBody CarDto carDto)
    {
        return new ResponseEntity(carService.saveNewCar(carDto),HttpStatus.CREATED);
        //return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping("car/{carId}")
    public ResponseEntity updateCar(@PathVariable("carId") UUID carId,@Validated @RequestBody CarDto carDto)
    {
        return new ResponseEntity(carService.updateCar(carId,carDto),HttpStatus.NO_CONTENT);
       // return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping(produces = {"application/json"},path = "car")
    public ResponseEntity<CarPagedList> listCars(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                                 @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                 @RequestParam(value = "carName", required = false) String carName,
                                                 @RequestParam(value = "carModel", required = false) CarModelEnum carModel,
                                                 @RequestParam(value = "invetoryInHand",required = false) Boolean inventoryInHand)
    {
        if(inventoryInHand == null)
        {
            inventoryInHand = false;
        }
        if (pageNumber == null || pageNumber < 0){
            pageNumber = DEFAULT_PAGE_NUMBER;
        }

        if (pageSize == null || pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        CarPagedList carPagedList = carService.listCars(carName,carModel, PageRequest.of(pageNumber,pageSize),inventoryInHand);
        return new ResponseEntity<>(carPagedList,HttpStatus.OK);
    }
    @GetMapping("carUpc/{upc}")
    public ResponseEntity<CarDto> getCarByUpc(@PathVariable("upc") String upc)
    {
        log.debug("****************");
        return new ResponseEntity<>(carService.getByUpc(upc),HttpStatus.OK);
        //return new ResponseEntity<>(HttpStatus.OK);
    }
}
