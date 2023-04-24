package com.example.task.service;

import com.example.task.domain.dto.ExchangeRateDataA;

import java.util.Optional;

public interface IMaxMinExchangeService {
    Optional<ExchangeRateDataA> getMinMaxExchangeByCode(String code, int N);
}
