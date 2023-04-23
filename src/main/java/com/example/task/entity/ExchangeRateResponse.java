package com.example.task.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Data
public class ExchangeRateResponse {

    @JsonProperty("table")
    private String table;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("code")
    private String code;

    @JsonProperty("rates")
    private List<ExchangeRateDetails> rates;


}
