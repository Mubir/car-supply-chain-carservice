package com.mubir.carservice.repositories;

import com.mubir.carservice.domain.Car;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface CarRepository extends PagingAndSortingRepository<Car, UUID> {
}
