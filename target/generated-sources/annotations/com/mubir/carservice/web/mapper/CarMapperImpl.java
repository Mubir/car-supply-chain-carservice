package com.mubir.carservice.web.mapper;

import com.mubir.carservice.domain.Car;
import com.mubir.carservice.domain.Car.CarBuilder;
import com.mubir.carservice.web.model.CarDto;
import com.mubir.carservice.web.model.CarDto.CarDtoBuilder;
import com.mubir.carservice.web.model.CarModelEnum;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-08-03T15:42:36+0900",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 11.0.4 (Oracle Corporation)"
)
@Component
public class CarMapperImpl implements CarMapper {

    @Autowired
    private DateMapper dateMapper;

    @Override
    public CarDto carToCarDto(Car car) {
        if ( car == null ) {
            return null;
        }

        CarDtoBuilder carDto = CarDto.builder();

        carDto.id( car.getId() );
        if ( car.getVersion() != null ) {
            carDto.version( car.getVersion().intValue() );
        }
        carDto.createdDate( dateMapper.asOffsetDateTime( car.getCreatedDate() ) );
        carDto.lastModifiedDate( dateMapper.asOffsetDateTime( car.getLastModifiedDate() ) );
        carDto.carName( car.getCarName() );
        if ( car.getCarModel() != null ) {
            carDto.carModel( Enum.valueOf( CarModelEnum.class, car.getCarModel() ) );
        }
        carDto.upc( car.getUpc() );
        carDto.price( car.getPrice() );

        return carDto.build();
    }

    @Override
    public Car carDtoToCar(CarDto carDto) {
        if ( carDto == null ) {
            return null;
        }

        CarBuilder car = Car.builder();

        car.id( carDto.getId() );
        if ( carDto.getVersion() != null ) {
            car.version( carDto.getVersion().longValue() );
        }
        car.createdDate( dateMapper.asTimestamp( carDto.getCreatedDate() ) );
        car.lastModifiedDate( dateMapper.asTimestamp( carDto.getLastModifiedDate() ) );
        car.carName( carDto.getCarName() );
        if ( carDto.getCarModel() != null ) {
            car.carModel( carDto.getCarModel().name() );
        }
        car.upc( carDto.getUpc() );
        car.price( carDto.getPrice() );

        return car.build();
    }
}
