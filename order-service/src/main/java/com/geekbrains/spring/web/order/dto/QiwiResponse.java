package com.geekbrains.spring.web.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QiwiResponse {
    private String responseUrl;
    private String billId;
}
