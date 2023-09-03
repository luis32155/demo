package com.luisangulo.demo.repository;


import com.luisangulo.demo.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
}
