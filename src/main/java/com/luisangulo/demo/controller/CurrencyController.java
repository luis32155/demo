package com.luisangulo.demo.controller;

import com.luisangulo.demo.entity.Currency;
import com.luisangulo.demo.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.OPTIONS})
@RequestMapping(value = "/currencies")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<Currency>> listCurrency(){
        List<Currency> currencies = currencyService.listAllCurrency();
        if(currencies.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(currencies);
    }
}
