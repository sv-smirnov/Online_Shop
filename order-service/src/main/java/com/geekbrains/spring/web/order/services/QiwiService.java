package com.geekbrains.spring.web.order.services;

import com.geekbrains.spring.web.order.entities.Order;
import com.qiwi.billpayments.sdk.model.MoneyAmount;
import com.qiwi.billpayments.sdk.model.in.CreateBillInfo;
import com.qiwi.billpayments.sdk.model.in.Customer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Currency;
import java.util.UUID;

@AllArgsConstructor
@Service
public class QiwiService {

    private final OrderService orderService;

    public CreateBillInfo createBill(Long orderId){
        Order order = orderService.findOrderById(orderId);
        CreateBillInfo billInfo = new CreateBillInfo(
        UUID.randomUUID().toString(),
                new MoneyAmount(
                        BigDecimal.valueOf(order.getTotalPrice()),
                        Currency.getInstance("RUB")), "Заказ № " + orderId,
                ZonedDateTime.now().plusDays(45),
                new Customer(order.getAddress(), orderId.toString(), order.getPhone()),
                "http://localhost:3000/front"
        );
                return billInfo;
    }
}
