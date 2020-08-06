package com.mubir.carservice.bootstrap;

import com.mubir.carservice.domain.Car;
import com.mubir.carservice.repositories.CarRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

//@Component
public class CarLoader implements CommandLineRunner
{
    public static final String CAR_1_UPC = "0631234200036";
    public static final String CAR_2_UPC = "0631234300019";
    public static final String CAR_3_UPC = "0083783375213";
    private final CarRepository carRepository;

    public CarLoader(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        generateSeedvalue();
    }

    private void generateSeedvalue() {
        if(carRepository.count()<=0)
        {
            carRepository.save(Car.builder()
                    .carModel("xzy")
                    .carName("BMW")
                    .upc(CAR_1_UPC)
                    .price(new BigDecimal("50000"))
                    .build());

            carRepository.save(Car.builder()
                    .carModel("xoxo")
                    .carName("TOYOTA")
                    .upc(CAR_2_UPC)
                    .price(new BigDecimal("50000"))
                    .build());
        }
        System.out.println(" $$$$$$$$$$$ "+carRepository.findAll().toString());
    }
}
