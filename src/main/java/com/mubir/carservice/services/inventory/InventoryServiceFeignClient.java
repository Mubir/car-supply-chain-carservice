package com.mubir.carservice.services.inventory;

import com.mubir.carservice.services.inventory.model.CarInventoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.UUID;

@FeignClient(name="inventory-service")
public interface InventoryServiceFeignClient {

    @RequestMapping(method = RequestMethod.GET,value = CarInventoryServiceRestTemplateImpl.INVENTORY_PATH)
    ResponseEntity<List<CarInventoryDto>> getOnHandInventory(@PathVariable UUID carId);
}
