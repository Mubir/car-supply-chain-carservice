package com.mubir.common.events;


import lombok.*;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Data
public class ValidateOrderRequest {
    private CarOrderDto carOrder;
}
