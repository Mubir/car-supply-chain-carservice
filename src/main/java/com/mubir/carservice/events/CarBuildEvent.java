package com.mubir.carservice.events;
import com.mubir.carservice.web.model.CarDto;
public class CarBuildEvent extends CarEvent{
    public CarBuildEvent(CarDto carDto)
    {
        super(carDto);
    }
}
