package com.example.task.controller;

import com.example.task.domain.dto.GetMaxMinExchangeResponse;
import com.example.task.domain.entity.ExchangeRateDataA;
import com.example.task.service.IMaxMinExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/maxminrates")
public class MaxMinExchangeController {

    @Autowired
    IMaxMinExchangeService exchangesService;

    @RequestMapping("/{code}/{n}")
    public ResponseEntity<GetMaxMinExchangeResponse> getMaxMinExchange(@PathVariable("code") String code,
                                                                       @PathVariable("n") int N){

        Optional<ExchangeRateDataA> exchangeRateData = exchangesService.getMinMaxExchangeByCode(code, N);

        return exchangeRateData
                .map(value -> ResponseEntity
                        .ok(GetMaxMinExchangeResponse
                                .entityToDtoMapper()
                                .apply(value)))
                .orElseGet(() -> ResponseEntity
                        .notFound()
                        .build());

    }

}
