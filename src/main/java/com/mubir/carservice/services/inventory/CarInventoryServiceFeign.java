package com.mubir.carservice.services.inventory;

import com.mubir.carservice.services.inventory.model.CarInventoryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
@Slf4j
@RequiredArgsConstructor
@Profile("local-discovery")
@Service
public class CarInventoryServiceFeign implements CarInventoryService{
    private final InventoryServiceFeignClient inventoryServiceFeignClient;


    @Override
    public Integer getOnhandInventory(UUID carId) {
        log.debug("calling inventory service :"+carId);

        ResponseEntity<List<CarInventoryDto>> responseEntity =
                inventoryServiceFeignClient.getOnHandInventory(carId);
        Integer onHand = Objects.requireNonNull(responseEntity.getBody())
                .stream()
                .mapToInt(CarInventoryDto::getQuantityOnHand)
                .sum();

        log.debug("car id "+ carId+" on hand : "+onHand);
        return onHand;

    }
}
