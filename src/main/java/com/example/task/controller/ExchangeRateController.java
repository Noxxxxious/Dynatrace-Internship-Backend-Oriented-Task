package com.example.task.controller;

import com.example.task.domain.dto.GetExchangeRateResponse;
import com.example.task.domain.dto.ExchangeRateDataA;
import com.example.task.service.IExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/api/exchanges")
public class ExchangeRateController {

    @Autowired
    IExchangeRateService exchangesService;

    @RequestMapping("/{code}/{date}")
    public ResponseEntity<GetExchangeRateResponse> getExchangeRate(@PathVariable("code") String code,
                                                                   @PathVariable("date") String date){

        Optional<ExchangeRateDataA> exchangeRateData = exchangesService
                .getExchangeRateDataByCodeAndDate(code, LocalDate.parse(date));

        return exchangeRateData
                .map(value -> ResponseEntity
                        .ok(GetExchangeRateResponse
                                .entityToDtoMapper()
                                .apply(value)))
                .orElseGet(() -> ResponseEntity
                        .notFound()
                        .build());

    }

}
