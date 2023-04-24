package com.example.task.service;

import com.example.task.entity.ExchangeRateData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Optional;

@Service("exchanges")
public class ExchangeRateService implements IExchangeRateService{
    @Autowired
    RestTemplate restTemplate;

    @Override
    public Optional<ExchangeRateData> getExchangeRateDataByCodeAndDate(String code, LocalDate date){

        String url = "http://api.nbp.pl/api/exchangerates/rates/A/{code}/{date}"
                .replace("{code}", code)
                .replace("{date}", date.toString());

        try {
            ResponseEntity<ExchangeRateData> responseEntity =
                    restTemplate.getForEntity(url, ExchangeRateData.class);
            return Optional.ofNullable(responseEntity.getBody());
        } catch (HttpClientErrorException ex) {
            return Optional.empty();
        }

    }

}
