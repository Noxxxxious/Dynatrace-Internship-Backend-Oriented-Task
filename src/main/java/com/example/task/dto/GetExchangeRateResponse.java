package com.example.task.dto;

import com.example.task.entity.ExchangeRateData;
import com.example.task.entity.ExchangeRateDetails;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;
import java.util.function.Function;

@Builder
public class GetExchangeRateResponse {

    @JsonProperty("rates")
    private final List<ExchangeRateDetails> rates;

    public static Function<ExchangeRateData, GetExchangeRateResponse> entityToDtoMapper(){
        return exchangeRateData -> GetExchangeRateResponse.builder()
                .rates(exchangeRateData.getRates())
                .build();
    }

}
