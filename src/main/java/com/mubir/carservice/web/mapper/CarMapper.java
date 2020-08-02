package com.mubir.carservice.web.mapper;

import com.mubir.carservice.domain.Car;
import com.mubir.carservice.web.model.CarDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface CarMapper {
    CarDto CarToCarDto(Car car);
    Car CarDtoToCar(CarDto carDto);
}
