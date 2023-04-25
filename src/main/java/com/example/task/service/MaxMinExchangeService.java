package com.example.task.service;

import com.example.task.domain.dto.ExchangeRateDataA;
import com.example.task.domain.dto.ExchangeRateDetailsA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
;
import java.util.List;
import java.util.Optional;

@Service("maxminrates")
public class MaxMinExchangeService implements IMaxMinExchangeService{

    private final RestTemplate restTemplate;

    public MaxMinExchangeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Optional<ExchangeRateDataA> getMinMaxExchangeByCode(String code, int N) {
        //excluding N larger than 255 from queries
        if (N > 255){
            return Optional.empty();
        }

        //request url
        String url = String.format("http://api.nbp.pl/api/exchangerates/rates/A/%s/last/%d", code, N);

        //querying data from NBP API
        try {
            ResponseEntity<ExchangeRateDataA> responseEntity =
                    restTemplate.getForEntity(url, ExchangeRateDataA.class);

            //find max and min average rates and insert into ExchangeRateData object
            ExchangeRateDataA exchangeRateData = responseEntity.getBody();
            assert exchangeRateData != null;
            exchangeRateData.setRates(findMaxMinValues(exchangeRateData));

            return Optional.of(exchangeRateData);
        } catch (HttpClientErrorException ex) {
            return Optional.empty();
        }

    }

    private List<ExchangeRateDetailsA> findMaxMinValues(ExchangeRateDataA exchangeRateData){
        ExchangeRateDetailsA max = new ExchangeRateDetailsA(), min = new ExchangeRateDetailsA();
        max.setExchangeRate(-Double.MAX_VALUE);
        min.setExchangeRate(Double.MAX_VALUE);

        assert exchangeRateData != null;
        for(ExchangeRateDetailsA exchangeRateDetails : exchangeRateData.getRates()){
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
