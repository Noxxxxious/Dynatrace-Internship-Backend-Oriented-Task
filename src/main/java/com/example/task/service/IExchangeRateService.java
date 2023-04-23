package com.example.task.service;

import com.example.task.entity.ExchangeRateResponse;

import java.time.LocalDate;

public interface IExchangeRateService {
    public ExchangeRateResponse getExchangeRateDetailsByCodeAndDate(String code,LocalDate date);
}
