package com.example.task.service;

import com.example.task.entity.ExchangeRateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Service("exchanges")
public class ExchangeRateService implements IExchangeRateService{
    @Autowired
    RestTemplate restTemplate;

    @Override
    public ExchangeRateResponse getExchangeRateDetailsByCodeAndDate(String code, LocalDate date){

        String url = "http://api.nbp.pl/api/exchangerates/rates/A/{code}/{date}"
                .replace("{code}", code)
                .replace("{date}", date.toString());

        try {
            ResponseEntity<ExchangeRateResponse> responseEntity =
                    restTemplate.getForEntity(url, ExchangeRateResponse.class);
            return responseEntity.getBody();
        } catch (HttpClientErrorException ex) {
            return null;
        }

    }
}
