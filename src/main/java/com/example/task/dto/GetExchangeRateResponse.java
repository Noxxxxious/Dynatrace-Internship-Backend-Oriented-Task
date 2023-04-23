package com.example.task.dto;

import com.example.task.entity.ExchangeRateData;
import com.example.task.entity.ExchangeRateDetails;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;
import java.util.function.Function;

@Builder
public class GetExchangeRateResponse {
    @JsonProperty("table")
    private final String table;

    @JsonProperty("currency")
    private final String currency;

    @JsonProperty("code")
    private final String code;

    @JsonProperty("rates")
    private final List<ExchangeRateDetails> rates;

    public static Function<ExchangeRateData, GetExchangeRateResponse> entityToDtoMapper(){
        return exchangeRateData -> GetExchangeRateResponse.builder()
                .table(exchangeRateData.getTable())
                .currency(exchangeRateData.getCurrency())
                .code(exchangeRateData.getCode())
                .rates(exchangeRateData.getRates())
                .build();
    }

}
