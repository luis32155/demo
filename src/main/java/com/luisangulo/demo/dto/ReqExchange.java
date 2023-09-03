package com.luisangulo.demo.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ReqExchange {
    private BigDecimal amount;
    private Long currencyFrom;
    private Long currencyTo;
}
