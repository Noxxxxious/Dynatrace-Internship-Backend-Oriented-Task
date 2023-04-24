package com.example.task.service;

import com.example.task.entity.ExchangeRateData;

import java.time.LocalDate;
import java.util.Optional;

public interface IMaxMinExchangeService {
    public Optional<ExchangeRateData> getMinMaxExchangeByCode(String code, int N);
}
