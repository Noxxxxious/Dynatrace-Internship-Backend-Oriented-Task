package com.example.task.service;

import com.example.task.entity.ExchangeRateData;
import com.example.task.entity.ExchangeRateDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
;
import java.util.List;
import java.util.Optional;

@Service("minmaxrates")
public class MaxMinExchangeService implements IMaxMinExchangeService{

    @Autowired
    RestTemplate restTemplate;

    @Override
    public Optional<ExchangeRateData> getMinMaxExchangeByCode(String code, int N) {

        //request url
        String url = "http://api.nbp.pl/api/exchangerates/rates/A/{code}/last/{n}"
                .replace("{code}", code)
                .replace("{n}", Integer.toString(N));

        //querying data from NBP API
        try {
            ResponseEntity<ExchangeRateData> responseEntity =
                    restTemplate.getForEntity(url, ExchangeRateData.class);

            //find max and min average rates and insert into ExchangeRateData object
            ExchangeRateData exchangeRateData = responseEntity.getBody();
            assert exchangeRateData != null;
            exchangeRateData.setRates(findMaxMinValues(exchangeRateData));

            return Optional.of(exchangeRateData);
        } catch (HttpClientErrorException ex) {
            return Optional.empty();
        }

    }

    private List<ExchangeRateDetails> findMaxMinValues(ExchangeRateData exchangeRateData){
        ExchangeRateDetails max = new ExchangeRateDetails(), min = new ExchangeRateDetails();
        max.setExchangeRate(-Double.MAX_VALUE);
        min.setExchangeRate(Double.MAX_VALUE);

        assert exchangeRateData != null;
        for(ExchangeRateDetails exchangeRateDetails : exchangeRateData.getRates()){
            if (exchangeRateDetails.getExchangeRate() > max.getExchangeRate()){
                max = exchangeRateDetails;
            }
            if (exchangeRateDetails.getExchangeRate() < min.getExchangeRate()){
                min = exchangeRateDetails;
            }
        }

        return List.of(max, min);
    }

}
