package com.mubir.carservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mubir.carservice.bootstrap.CarLoader;
import com.mubir.carservice.services.CarService;
import com.mubir.carservice.web.model.CarDto;
import com.mubir.carservice.web.model.CarModelEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CarController.class) //often forget so careful
class CarControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    CarService carService;

    @Test
    void getCarById() throws Exception {
        given(carService.getById(any(),anyBoolean())).willReturn(getMocObject());
        mockMvc.perform(get("/api/v1/car/"+ UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());

    }

    @Test
    void createCar() throws Exception {

        CarDto carDto = getMocObject();
        String carDtoJson = objectMapper.writeValueAsString(carDto);
        given(carService.getById(any(),anyBoolean())).willReturn(getMocObject());
        mockMvc.perform(post("/api/v1/car/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(carDtoJson))
                .andExpect(status().isCreated());
    }

    @Test
    void updateCar() throws Exception{
        given(carService.getById(any(),anyBoolean())).willReturn(getMocObject());
        CarDto carDto = getMocObject();
        String carDtoJson = objectMapper.writeValueAsString(carDto);

        mockMvc.perform(put("/api/v1/car/"+UUID.randomUUID().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(carDtoJson))
                .andExpect(status().isNoContent());

    }

    CarDto getMocObject()
    {
        return CarDto.builder()
                .carName("abcc")
                .carModel(CarModelEnum.BMW)
                .upc(CarLoader.CAR_1_UPC)
                .price(new BigDecimal("1234"))
                .build();
    }
}