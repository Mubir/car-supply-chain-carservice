package com.mubir.carservice.services.order;

import com.mubir.carservice.repositories.CarRepository;
import com.mubir.common.events.CarOrderDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@RequiredArgsConstructor
@Slf4j
@Component
public class CarOrderValidator {
    private final CarRepository carRepository;

    public Boolean validateOrder(CarOrderDto carOrderDto)
    {
        AtomicInteger carsNotFound = new AtomicInteger();
        carOrderDto.getCarOrderLines().forEach( orderLine ->{
            if(carRepository.findByUpc(orderLine.getUpc())==null)
            {
                carsNotFound.incrementAndGet();
            }
        });

        return carsNotFound.get() ==0;
    }
}
