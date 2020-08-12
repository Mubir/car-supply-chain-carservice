package com.mubir.common.events;

import com.mubir.carservice.web.model.CarDto;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarEvent implements Serializable {
    static final long serialVersionUID = -3756037297000975426L;

    private CarDto carDto;
}

