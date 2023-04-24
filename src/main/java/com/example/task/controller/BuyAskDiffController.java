package com.example.task.controller;

import com.example.task.dto.GetBuyAskDiffResponse;
import com.example.task.entity.ExchangeRateDataC;
import com.example.task.service.IBuyAskDiffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/buyaskdiff")
public class BuyAskDiffController {

    @Autowired
    IBuyAskDiffService exchangesService;

    @RequestMapping("{code}/{n}")
    public ResponseEntity<GetBuyAskDiffResponse> getLargestBuyAskDiff(@PathVariable("code") String code, @PathVariable("n") int N){

        Optional<ExchangeRateDataC> exchangeRateData = exchangesService.getLargestBuyAskDiffRateByCode(code, N);

        return exchangeRateData
                .map(value -> ResponseEntity
                        .ok(GetBuyAskDiffResponse
                                .entityToDtoMapper()
                                .apply(value)))
                .orElseGet(() -> ResponseEntity
                        .notFound()
                        .build());

    }
}
