package com.mubir.carservice.web.mapper;

import com.mubir.carservice.domain.Car;
import com.mubir.carservice.web.model.CarDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
@DecoratedWith(CarMapperDecorator.class)
@Mapper(uses = {DateMapper.class})
public interface CarMapper {
    CarDto carToCarDto(Car car);
    Car carDtoToCar(CarDto carDto);
    CarDto carDtoWithInventory(Car car);
}
