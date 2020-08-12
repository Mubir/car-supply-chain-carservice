package com.mubir.carservice.services.building;

import com.mubir.carservice.config.JmsConfig;
import com.mubir.carservice.domain.Car;
import com.mubir.common.events.BuildCarEvent;
import com.mubir.common.events.NewInventoryEvent;
import com.mubir.carservice.repositories.CarRepository;
import com.mubir.carservice.web.model.CarDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class BuildCarListener {

    private final CarRepository carRepository;
    private final JmsTemplate jmsTemplate;
    @Transactional
    @JmsListener(destination = JmsConfig.DEV_CAR_BUILD)
    public void listen(BuildCarEvent event)
    {
        CarDto carDto = event.getCarDto();
        Car car = carRepository.getOne(carDto.getId());

        carDto.setQuantityOnHand(car.getQuantityPreOrder());
       NewInventoryEvent newInventoryEvent = new NewInventoryEvent(carDto);
        jmsTemplate.convertAndSend(JmsConfig.DEV_INVENTORY_QUEUE,newInventoryEvent);

    }
}
