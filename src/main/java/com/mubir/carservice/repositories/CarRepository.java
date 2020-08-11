package com.mubir.carservice.repositories;

import com.mubir.carservice.domain.Car;
import com.mubir.carservice.web.model.CarModelEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface CarRepository extends JpaRepository<Car, UUID> {
    Page<Car> findAllByCarName(String carName, Pageable pageable);
    Page<Car> findAllByCarModel(CarModelEnum carModel, Pageable pageable);
    Page<Car> findAllByCarNameAndCarModel(String carName,CarModelEnum carModel, Pageable pageable);
    Car findByUpc(String upc);
}
