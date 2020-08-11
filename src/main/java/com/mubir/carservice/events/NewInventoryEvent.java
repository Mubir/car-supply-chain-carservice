package com.mubir.carservice.events;
import com.mubir.carservice.web.model.CarDto;

public class NewInventoryEvent extends CarEvent {
    NewInventoryEvent(CarDto carDto) {
        super(carDto);
    }
}
