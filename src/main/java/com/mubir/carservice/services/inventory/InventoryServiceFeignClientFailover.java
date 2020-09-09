package com.mubir.carservice.services.inventory;

import com.mubir.carservice.services.inventory.model.CarInventoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;
@RequiredArgsConstructor
@Component
public class InventoryServiceFeignClientFailover implements InventoryServiceFeignClient{
    private final InventoryFailoverFeignClient failoverFeignClient;


    @Override
    public ResponseEntity<List<CarInventoryDto>> getOnHandInventory(UUID carId) {
        return failoverFeignClient.getOnhandInventory();
    }
}
