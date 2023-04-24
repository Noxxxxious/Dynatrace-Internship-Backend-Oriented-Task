package com.example.task.service;

import com.example.task.domain.entity.ExchangeRateDataA;

import java.time.LocalDate;
import java.util.Optional;

public interface IExchangeRateService {
    public Optional<ExchangeRateDataA> getExchangeRateDataByCodeAndDate(String code, LocalDate date);
}
