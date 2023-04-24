package com.example.task.service;

import com.example.task.domain.dto.ExchangeRateDataC;

import java.util.Optional;

public interface IBuyAskDiffService {
    Optional<ExchangeRateDataC> getLargestBuyAskDiffRateByCode(String code, int N);
}
