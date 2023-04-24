package com.example.task.service;

import com.example.task.domain.entity.ExchangeRateDataC;
import com.example.task.domain.entity.ExchangeRateDetailsC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service("buyaskdiff")
public class BuyAskDiffService implements IBuyAskDiffService{

    @Autowired
    RestTemplate restTemplate;

    @Override
    public Optional<ExchangeRateDataC> getLargestBuyAskDiffRateByCode(String code, int N){
        //excluding N larger than 255 from queries
        if (N > 255){
            return Optional.empty();
        }

        //request url
        String url = "http://api.nbp.pl/api/exchangerates/rates/C/{code}/last/{n}"
                .replace("{code}", code)
                .replace("{n}", Integer.toString(N));

        try {
            ResponseEntity<ExchangeRateDataC> responseEntity =
                    restTemplate.getForEntity(url, ExchangeRateDataC.class);

            //find major difference between the buy and ask rate and insert into ExchangeRateData object
            ExchangeRateDataC exchangeRateData = responseEntity.getBody();
            assert exchangeRateData != null;
            exchangeRateData.setRates(List.of(Objects.requireNonNull(findLargestBuyAskDiff(exchangeRateData))));

            return Optional.of(exchangeRateData);
        } catch (HttpClientErrorException ex) {
            return Optional.empty();
        }

    }

    private ExchangeRateDetailsC findLargestBuyAskDiff(ExchangeRateDataC exchangeRateData){
        double maxDiff = 0.0;
        ExchangeRateDetailsC resultDetails = new ExchangeRateDetailsC();

        assert exchangeRateData != null;
        for(ExchangeRateDetailsC exchangeRateDetails : exchangeRateData.getRates()){
            double diff = Math.abs(exchangeRateDetails.getAsk() - exchangeRateDetails.getBid());
            if (diff >= maxDiff){
                maxDiff = diff;
                resultDetails = exchangeRateDetails;
            }
        }
        return resultDetails;

    }

}
