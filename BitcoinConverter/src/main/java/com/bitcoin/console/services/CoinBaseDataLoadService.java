package com.bitcoin.console.services;

import java.time.Duration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CoinBaseDataLoadService implements ICoinBaseDataLoadService{

	private final RestTemplate restTemplate;

    public CoinBaseDataLoadService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder
                .setConnectTimeout(Duration.ofSeconds(500))
                .setReadTimeout(Duration.ofSeconds(500))
                .build();
    }
    
    public String getBitCoinHistoricalDataForCurrency(String startDate, String endDate, String currency) {
    	String url = "https://api.coindesk.com/v1/bpi/historical/close.json?start={startDate}&end={endDate}&currency={currency}";
    	try{
    		ResponseEntity<String> response = this.restTemplate.getForEntity(url, String.class, startDate, endDate, currency);
    		if (response.getStatusCode() == HttpStatus.OK) {
    			return response.getBody();
    		} else {
    			return null;
    		}
    	}
    	catch(Exception e){
    		return null;
    	}
    }
    
    public String getCurrentBitCoinRateForCurrency(String currency) {
        String url = "https://api.coindesk.com/v1/bpi/currentprice/{currency}.json";
        try{
        ResponseEntity<String> response = this.restTemplate.getForEntity(url, String.class,currency);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            return null;
        }
        }catch(Exception e){
        	return null;
        }
    }
    
}
