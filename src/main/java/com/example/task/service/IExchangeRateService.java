package com.example.task.service;

import com.example.task.domain.dto.ExchangeRateDataA;

import java.time.LocalDate;
import java.util.Optional;

public interface IExchangeRateService {
    Optional<ExchangeRateDataA> getExchangeRateDataByCodeAndDate(String code, LocalDate date);
}
