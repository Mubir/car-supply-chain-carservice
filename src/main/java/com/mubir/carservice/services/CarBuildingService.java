package com.mubir.carservice.services;

import com.mubir.carservice.config.JmsConfig;
import com.mubir.carservice.domain.Car;
import com.mubir.carservice.events.CarBuildEvent;
import com.mubir.carservice.repositories.CarRepository;
import com.mubir.carservice.services.inventory.CarInventoryService;
import com.mubir.carservice.web.mapper.CarMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarBuildingService {

    private final CarRepository carRepository;
    private final CarInventoryService carInventoryService;
    private final JmsTemplate jmsTemplate;
    private final CarMapper carMapper;
    @Scheduled(fixedRate = 5000) //seconds in mili-second
    public void checkForLowInventory()
    {
        List<Car> cars = carRepository.findAll();

        cars.forEach( car-> {
            Integer amount = carInventoryService.getOnhandInventory(car.getId());

            log.warn("Min :"+car.getMinOnHand());
            log.warn("inv "+amount);

            if(car.getMinOnHand()>= amount)
            {
                jmsTemplate.convertAndSend(JmsConfig.DEV_CAR_BUILD,new CarBuildEvent(carMapper.carToCarDto(car)));
            }
        });
    }
}
