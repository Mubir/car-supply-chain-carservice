package com.mubir.carservice.services.order;

import com.mubir.carservice.config.JmsConfig;
import com.mubir.common.events.ValidateOrderRequest;
import com.mubir.common.events.ValidateOrderResult;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CarOrderValidationListener {

    private final CarOrderValidator carOrderValidator;
    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.VALIDATE_ORDER_QUEUE)
    public void listen(ValidateOrderRequest validateOrderRequest)
    {
        Boolean isValid = carOrderValidator.validateOrder(validateOrderRequest.getCarOrder());

        jmsTemplate.convertAndSend(JmsConfig.VALIDATE_ORDER_RESPONSE_QUEUE,
                ValidateOrderResult.builder()
        .isValid(isValid)
        .orderId(validateOrderRequest.getCarOrder().getId()).build());
    }
}
