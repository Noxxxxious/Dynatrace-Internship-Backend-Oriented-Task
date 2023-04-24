package com.example.task.service;

import com.example.task.entity.ExchangeRateData;

import java.time.LocalDate;
import java.util.Optional;

public interface IExchangeRateService {
    public Optional<ExchangeRateData> getExchangeRateDataByCodeAndDate(String code, LocalDate date);
}
