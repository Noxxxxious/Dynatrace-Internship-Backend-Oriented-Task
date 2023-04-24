package com.example.task.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.function.Function;

@Builder
public class GetExchangeRateResponse {

    @JsonProperty("exchangeRate")
    private final double exchangeRate;

    public static Function<ExchangeRateDataA, GetExchangeRateResponse> entityToDtoMapper(){
        return exchangeRateData -> GetExchangeRateResponse.builder()
                .exchangeRate(exchangeRateData.getRates().get(0).getExchangeRate())
                .build();
    }

}
