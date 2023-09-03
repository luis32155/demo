package com.luisangulo.demo.controller;


import com.luisangulo.demo.dto.ReqExchange;
import com.luisangulo.demo.dto.ReqExchangeUpdate;
import com.luisangulo.demo.dto.ResExchange;
import com.luisangulo.demo.entity.Exchange;
import com.luisangulo.demo.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT, RequestMethod.OPTIONS})
@RequestMapping(value = "/exchange")
public class ExchangeController {

    @Autowired
    private ExchangeService exchangeService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<Exchange>> listExchangeById(@RequestParam(name = "exchangeFrom", required = false) Long exchangeFrom){
        if(exchangeFrom!=null){
            List<Exchange> exchange = exchangeService.listExchangeById(exchangeFrom);
            if(exchange.isEmpty()){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(exchange);
        }else{
            List<Exchange> exchange = exchangeService.listAllExchange();
            if(exchange.isEmpty()){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(exchange);
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ResExchange> convertExchange(@RequestBody ReqExchange data){
        return ResponseEntity.ok(exchangeService.convertAmountExchange(data));
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Exchange> updateRateExchange(@PathVariable Long id, @RequestBody ReqExchangeUpdate data){
        if (exchangeService.updateRateExchange(id, data)==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(exchangeService.updateRateExchange(id, data));
    }
}
