package com.geekbrains.spring.web.order.controllers;

import com.geekbrains.spring.web.order.dto.QiwiResponse;
import com.geekbrains.spring.web.order.services.QiwiService;
import com.qiwi.billpayments.sdk.client.BillPaymentClient;
import com.qiwi.billpayments.sdk.client.BillPaymentClientFactory;
import com.qiwi.billpayments.sdk.model.out.BillResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/v1/qiwi")
@RequiredArgsConstructor
@Slf4j
public class QiwiController {
//    @Value("${qiwi.public-key}")
//    private String publicKey;
//    @Value("${qiwi.secret-key}")
//    private String secretKey;

    private final QiwiService qiwiService;
    private String secretKey = "eyJ2ZXJzaW9uIjoiUDJQIiwiZGF0YSI6eyJwYXlpbl9tZXJjaGFudF9zaXRlX3VpZCI6Ijc5OWVibC0wMCIsInVzZXJfaWQiOiI3OTE5MjMwMTI0NCIsInNlY3JldCI6ImUyNmYwNDg4NTEwZGVkNTI0M2VmMzhjNDAxMjQ2MWUzMmM1ZjQ3Y2U5NzExOTIwODJkMzE2MTlmNTA2NDlhOGUifX0=";
    private BillPaymentClient billPaymentClient = BillPaymentClientFactory.createDefault(secretKey);

    //ToDo - заменить secretKey на свой, после регистрации на QIWI

    //Добавить статусы "Оплачен\не оплачен" в наш Ордер
    // В зависимости от успеха операции, менять этот статус
    // Оплаченные заказы не долны быть оплачены дважды

    @GetMapping("/create/{orderId}")
    public QiwiResponse createOrder(@PathVariable Long orderId) throws URISyntaxException {
        BillResponse response = billPaymentClient.createBill(qiwiService.createBill(orderId));
        log.info("resp = {}", response);
        return new QiwiResponse(response.getPayUrl(), response.getBillId());
    }

    @PostMapping("/capture/{billId}")
    public ResponseEntity<?> captureOrder(@PathVariable String billId) throws IOException {
        BillResponse response = billPaymentClient.getBillInfo(billId);
        if("COMPLETED".equals(response.getStatus())) {
            //ToDo сделать обработку статуса
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
