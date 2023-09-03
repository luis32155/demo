package com.luisangulo.demo.service;


import com.luisangulo.demo.dto.ReqExchange;
import com.luisangulo.demo.dto.ReqExchangeUpdate;
import com.luisangulo.demo.dto.ResExchange;
import com.luisangulo.demo.entity.Exchange;

import java.util.List;

public interface ExchangeService {
    public List<Exchange> listAllExchange();
    public List<Exchange> listExchangeById(Long exchangeId);
    public ResExchange convertAmountExchange(ReqExchange exchange);
    public Exchange updateRateExchange(Long exchangeId, ReqExchangeUpdate data);
}
