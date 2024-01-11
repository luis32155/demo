package com.luisangulo.demo.service.impl;


import com.luisangulo.demo.dto.ReqExchange;
import com.luisangulo.demo.dto.ReqExchangeUpdate;
import com.luisangulo.demo.dto.ResExchange;
import com.luisangulo.demo.entity.Exchange;
import com.luisangulo.demo.repository.ExchangeRepository;
import com.luisangulo.demo.service.ExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExchangeServiceImpl implements ExchangeService {
    private final ExchangeRepository exchangeRepository;

    @Override
    public List<Exchange> listAllExchange() {
        return exchangeRepository.findAll();
    }

    @Override
    public List<Exchange> listExchangeById(Long exchangeId) {
        return exchangeRepository.findExchangeByCurrencyChangeFrom(exchangeId);
    }

    @Override
    public ResExchange convertAmountExchange(ReqExchange exchange) {
        return Optional.ofNullable(exchangeRepository.finExchangedByCurrencyChangeFromAndCurrencyChangeTo(exchange))
                .map(exchangeRes -> {
                    ResExchange resExchange = new ResExchange();
                    resExchange.setAmountTypeExchange(exchangeRes.getExchange_rate().multiply(exchange.getAmount()));
                    resExchange.setAmount(exchange.getAmount());
                    resExchange.setExchangeRate(exchangeRes.getExchange_rate());
                    resExchange.setCurrencyTo(exchange.getCurrencyTo());
                    resExchange.setCurrencyFrom(exchange.getCurrencyFrom());
                    return resExchange;
                })
                .orElse(null);
    }

    @Override
    public Exchange updateRateExchange(Long exchangeId, ReqExchangeUpdate data) {
        return Optional.ofNullable(exchangeRepository.getById(exchangeId))
                .filter(exchange -> data != null)
                .map(exchange -> {
                    exchange.setExchange_rate(data.getExchangeRate());
                    exchange.setExchange_update(new Date());
                    return exchangeRepository.save(exchange);
                })
                .orElse(null);
    }
}
