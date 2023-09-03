package com.luisangulo.demo.service;


import com.luisangulo.demo.entity.Currency;
import com.luisangulo.demo.repository.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;

    @Override
    public List<Currency> listAllCurrency() {
        return currencyRepository.findAll();
    }
}
