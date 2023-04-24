package com.example.task.dto;

import com.example.task.entity.ExchangeRateData;
import com.example.task.entity.ExchangeRateDetails;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;
import java.util.function.Function;

@Builder
public class GetMaxMinExchangeResponse {

    @JsonProperty("rates")
    private final List<ExchangeRateDetails> rates;

    public static Function<ExchangeRateData, GetMaxMinExchangeResponse> entityToDtoMapper(){
        return exchangeRateData -> GetMaxMinExchangeResponse.builder()
                .rates(exchangeRateData.getRates())
                .build();
    }

}
