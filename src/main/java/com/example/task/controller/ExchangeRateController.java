package com.example.task.controller;

import com.example.task.entity.ExchangeRateResponse;
import com.example.task.service.IExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/exchanges")
public class ExchangeRateController {

    @Autowired
    IExchangeRateService exchanges;

    @RequestMapping("{code}/{date}")
    public ExchangeRateResponse getExchangeRateByCurrencyCode(@PathVariable("code") String code, @PathVariable("date") String date){

        ExchangeRateResponse response;
        response = exchanges.getExchangeRateDetailsByCodeAndDate(code, LocalDate.parse(date));

        return response;
    }
}
