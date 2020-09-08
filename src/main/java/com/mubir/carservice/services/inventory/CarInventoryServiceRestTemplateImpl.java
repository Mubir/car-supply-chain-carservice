package com.mubir.carservice.services.inventory;

import com.mubir.carservice.services.inventory.model.CarInventoryDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
@Profile("!local-discovery")
@Component
@Slf4j
@ConfigurationProperties(prefix = "mubir.micro",ignoreUnknownFields = false)
public class CarInventoryServiceRestTemplateImpl implements CarInventoryService{
    public static final String INVENTORY_PATH = "/api/v1/car/{carId}/inventory";
    private final RestTemplate restTemplate;
    private String carInventoryServiceHost;

    public void setCarInventoryServiceHost(String carInventoryServiceHost) {
        this.carInventoryServiceHost = carInventoryServiceHost;
    }

    public CarInventoryServiceRestTemplateImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public Integer getOnhandInventory(UUID carId) {
        log.debug("inventory is called");

        ResponseEntity<List<CarInventoryDto>> responseEntity = restTemplate.exchange(
                carInventoryServiceHost + INVENTORY_PATH, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<CarInventoryDto>>() {},(Object) carId);
                // carId will replace {carId} in url
        log.warn(responseEntity.getBody().toString());
        Integer onHand = Objects.requireNonNull(responseEntity.getBody())
                .stream()
                .mapToInt(CarInventoryDto::getQuantityOnHand)
                .sum();
        log.error(onHand.toString());
        return onHand;
    }
}
