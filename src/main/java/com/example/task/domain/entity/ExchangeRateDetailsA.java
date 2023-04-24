package com.example.task.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Data
public class ExchangeRateDetailsA {

    @JsonProperty("no")
    private String no;

    @JsonProperty("effectiveDate")
    private LocalDate effectiveDate;

    @JsonProperty("mid")
    private double exchangeRate;
}
