package com.example.task.service;

import com.example.task.domain.entity.ExchangeRateDataC;

import java.util.Optional;

public interface IBuyAskDiffService {
    public Optional<ExchangeRateDataC> getLargestBuyAskDiffRateByCode(String code, int N);
}
