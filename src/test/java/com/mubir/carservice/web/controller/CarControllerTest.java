package com.mubir.carservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mubir.carservice.web.model.CarDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(CarController.class) //often forget so careful
class CarControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @Test
    void getCarById() throws Exception {

        mockMvc.perform(get("/api/v1/car/"+ UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());

    }

    @Test
    void createCar() throws Exception {
        CarDto carDto = CarDto.builder().build();
        String carDtoJson = objectMapper.writeValueAsString(carDto);

        mockMvc.perform(post("/api/v1/car/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(carDtoJson))
                .andExpect(status().isCreated());
    }

    @Test
    void updateCar() throws Exception{

        CarDto carDto = CarDto.builder().build();
        String carDtoJson = objectMapper.writeValueAsString(carDto);

        mockMvc.perform(put("/api/v1/car/"+UUID.randomUUID().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(carDtoJson))
                .andExpect(status().isOk());

    }
}