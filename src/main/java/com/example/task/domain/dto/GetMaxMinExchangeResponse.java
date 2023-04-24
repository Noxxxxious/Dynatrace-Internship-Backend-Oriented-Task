package com.example.task.domain.dto;

import com.example.task.domain.entity.ExchangeRateDataA;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.function.Function;

@Builder
public class GetMaxMinExchangeResponse {

    @JsonProperty("maxRate")
    private final double maxRate;

    @JsonProperty("minRate")
    private final double minRate;

    public static Function<ExchangeRateDataA, GetMaxMinExchangeResponse> entityToDtoMapper(){
        return exchangeRateData -> GetMaxMinExchangeResponse.builder()
                .maxRate(exchangeRateData.getRates().get(0).getExchangeRate())
                .minRate(exchangeRateData.getRates().get(1).getExchangeRate())
                .build();
    }

}
