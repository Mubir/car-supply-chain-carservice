package com.mubir.carservice.bootstrap;

import com.mubir.carservice.domain.Car;
import com.mubir.carservice.repositories.CarRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CarLoader implements CommandLineRunner
{
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
                    .upc(337010000001L)
                    .price(new BigDecimal("50000"))
                    .build());

            carRepository.save(Car.builder()
                    .carModel("xoxo")
                    .carName("TOYOTA")
                    .upc(33701000000L)
                    .price(new BigDecimal("50000"))
                    .build());
        }
        System.out.println(" $$$$$$$$$$$ "+carRepository.count());
    }
}
