package com.mubir.carservice.services.inventory;

import java.util.UUID;

public interface CarInventoryService {
    Integer getOnhandInventory(UUID carId);
}
