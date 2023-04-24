package com.example.task.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.function.Function;

@Builder
public class GetBuyAskDiffResponse {

    @JsonProperty("majorDiff")
    private final double majorDiff;

    public static Function<ExchangeRateDataC, GetBuyAskDiffResponse> entityToDtoMapper(){
        return exchangeRateData -> GetBuyAskDiffResponse.builder()
                .majorDiff(Math.abs(
                        exchangeRateData.getRates().get(0).getAsk() - exchangeRateData.getRates().get(0).getBid()))
                .build();
    }
}
