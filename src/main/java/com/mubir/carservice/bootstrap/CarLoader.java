package com.mubir.carservice.bootstrap;

import com.mubir.carservice.domain.Car;
import com.mubir.carservice.repositories.CarRepository;
import com.mubir.carservice.web.model.CarModelEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class CarLoader implements CommandLineRunner
{
    public static final String CAR_1_UPC = "0631234200036";
    public static final String CAR_2_UPC = "0631234300019";
    public static final String CAR_3_UPC = "0083783375213";
    private final CarRepository carRepository;

    @Override
    public void run(String... args) throws Exception {
        if(carRepository.count()==0) {
            generateSeedvalue();
        }
    }

    private void generateSeedvalue() {


            Car c1=Car.builder()
                    .carModel(CarModelEnum.NISSAN.name())
                    .carName("T1z")
                    .upc(CAR_1_UPC)
                    .price(new BigDecimal("50000"))
                    .minOnHand(66)
                    .quantityPreOrder(88)
                    .build();

            Car c2=Car.builder()
                    .carModel(CarModelEnum.TOYOTA.name())
                    .carName("T-sytle")
                    .upc(CAR_2_UPC)
                    .price(new BigDecimal("50000"))
                    .minOnHand(123)
                    .quantityPreOrder(23)
                    .build();
            Car c3=Car.builder()
                    .carModel(CarModelEnum.BMW.name())
                    .carName("S-cube")
                    .upc(CAR_3_UPC)
                    .price(new BigDecimal("50000"))
                    .minOnHand(99)
                    .quantityPreOrder(6)
                    .build();

        carRepository.save(c1);
        carRepository.save(c2);
        carRepository.save(c3);

       /* Car c3=Car.builder()
                .carModel("CarModelEnum.BMW.name()")
                .carName("S-cube")
                .upc(CAR_3_UPC)
                .price(new BigDecimal("50000"))
                .minOnHand(3)
                .quantityPreOrder(6)
                .build();

        carRepository.save(c3);
        */

        System.out.println(" $$$$$$$$$$$ "+carRepository.findAll().toString());
    }
}
