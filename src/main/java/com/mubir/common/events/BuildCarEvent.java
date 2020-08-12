package com.mubir.common.events;
import com.mubir.carservice.web.model.CarDto;
import lombok.NoArgsConstructor;
@NoArgsConstructor
public class BuildCarEvent extends CarEvent{
    public BuildCarEvent(CarDto carDto)
    {
        super(carDto);
    }
}
