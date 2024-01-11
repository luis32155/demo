package com.luisangulo.demo.service.impl;


import com.luisangulo.demo.entity.Currency;
import com.luisangulo.demo.repository.CurrencyRepository;
import com.luisangulo.demo.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;

    @Override
    public List<Currency> listAllCurrency() {
        return currencyRepository.findAll().stream()
                .collect(Collectors.toList());
    }
}
