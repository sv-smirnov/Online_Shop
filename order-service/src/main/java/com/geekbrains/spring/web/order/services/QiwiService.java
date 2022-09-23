package com.geekbrains.spring.web.order.services;

import com.geekbrains.spring.web.order.entities.Order;
import com.qiwi.billpayments.sdk.model.MoneyAmount;
import com.qiwi.billpayments.sdk.model.in.CreateBillInfo;
import com.qiwi.billpayments.sdk.model.in.Customer;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Currency;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class QiwiService {

    private final OrderService orderService;

    public CreateBillInfo createBill(Long orderId){
        Order order = orderService.findOrderById(orderId);
        CreateBillInfo billInfo = new CreateBillInfo(
        UUID.randomUUID().toString(),
                new MoneyAmount(
                        BigDecimal.valueOf(1),
                        Currency.getInstance("RUB")), "Заказ № " + orderId,
                ZonedDateTime.now().plusDays(45),
                new Customer(orderService.findOrderById(orderId).getAddress(), UUID.randomUUID().toString(), orderService.findOrderById(orderId).getPhone()),
                "http://localhost:3000/front/#!/store"
        );
        orderService.findOrderById(orderId).setBillId(billInfo.getBillId());
                return billInfo;
    }
    public void makePaid (String billId) {
        orderService.changeOrderStatusByBillId(billId,"COMPLETED");
    }
}
