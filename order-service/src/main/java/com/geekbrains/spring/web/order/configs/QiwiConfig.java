package com.geekbrains.spring.web.order.configs;

import com.qiwi.billpayments.sdk.client.BillPaymentClient;
import com.qiwi.billpayments.sdk.client.BillPaymentClientFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class QiwiConfig {
    @Value("${qiwi.public-key}")
    private String publicKey;
    @Value("${qiwi.secret-key}")
    private String secretKey;

    private BillPaymentClient billPaymentClient;

    @Bean
    public BillPaymentClient billPaymentClient(){
        return BillPaymentClientFactory.createDefault(secretKey);
    }
}
