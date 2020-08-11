package com.mubir.carservice.events;

import com.mubir.carservice.web.model.CarDto;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@RequiredArgsConstructor
public class CarEvent implements Serializable {
    static final long serialVersionUID =-5781515597148163111L;

    private final CarDto carDto;
}

