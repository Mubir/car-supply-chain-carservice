package com.mubir.common.events;
import com.mubir.carservice.web.model.CarDto;
import lombok.NoArgsConstructor;
@NoArgsConstructor
public class NewInventoryEvent extends CarEvent {
   public NewInventoryEvent(CarDto carDto) {
        super(carDto);
    }
}
