package com.example.task.service;

import com.example.task.entity.ExchangeRateDataA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Optional;

@Service("exchanges")
public class ExchangeRateService implements IExchangeRateService{
    @Autowired
    RestTemplate restTemplate;

    @Override
    public Optional<ExchangeRateDataA> getExchangeRateDataByCodeAndDate(String code, LocalDate date){

        //excluding weekends from the queries - no data
        if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY){
            return Optional.empty();
        }

        //request url
        String url = "http://api.nbp.pl/api/exchangerates/rates/A/{code}/{date}"
                .replace("{code}", code)
                .replace("{date}", date.toString());

        //querying data from NBP API
        try {
            ResponseEntity<ExchangeRateDataA> responseEntity =
                    restTemplate.getForEntity(url, ExchangeRateDataA.class);
            return Optional.ofNullable(responseEntity.getBody());
        } catch (HttpClientErrorException ex) {
            return Optional.empty();
        }

    }

}
