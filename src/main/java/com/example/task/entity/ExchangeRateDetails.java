package com.example.task.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Data
public class ExchangeRateDetails {

    @JsonProperty("no")
    private String no;

    @JsonProperty("effectiveDate")
    private LocalDate effectiveDate;

    @JsonProperty("mid")
    private double exchangeRate;
}
