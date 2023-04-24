package com.example.task.service;

import com.example.task.entity.ExchangeRateDataA;

import java.util.Optional;

public interface IMaxMinExchangeService {
    public Optional<ExchangeRateDataA> getMinMaxExchangeByCode(String code, int N);
}
