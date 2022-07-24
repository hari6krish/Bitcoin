package com.bitcoin.console.services;

import java.util.Currency;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import com.bitcoin.console.model.BitcoinRate;
import com.bitcoin.console.util.DateUtil;
import com.bitcoin.console.util.JsonUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BitcoinRateStoreService implements IBitcoinRateStoreService{
	
	Logger logger = LoggerFactory.getLogger(BitcoinRateStoreService.class);
 
	
	@Autowired
	private ICoinBaseDataLoadService coinBaseDataLoadService;
	
	private ConcurrentHashMap<String,BitcoinRate> rateStore = new ConcurrentHashMap();
	
	public void populateBitCoinRates(){
		
		Set<Currency> currencies =  Currency.getAvailableCurrencies(); 
		for(Currency currency : currencies){
			String ccyCode = currency.getCurrencyCode();
			String currentData = coinBaseDataLoadService.getCurrentBitCoinRateForCurrency(currency.getCurrencyCode());
			String historicalData =  coinBaseDataLoadService.getBitCoinHistoricalDataForCurrency(
					DateUtil.getPreviousDateString(30), DateUtil.getCurrentDateString(), ccyCode);
			if(currentData==null || historicalData==null){
				continue;
			}
			BitcoinRate bitcoinRate = new BitcoinRate();
			try{
				bitcoinRate.setCurrency(ccyCode);	
				bitcoinRate.setCurrentRate(JsonUtil.mapCurrentRate(currentData, ccyCode));
				bitcoinRate = JsonUtil.populateHistoricData(bitcoinRate, historicalData, ccyCode);
			logger.info(bitcoinRate.toString());
			}catch(Exception e){
				continue;
			}
			rateStore.put(ccyCode, bitcoinRate);
			}
	logger.info("Rate Store processed");
	}

	public ConcurrentHashMap<String, BitcoinRate> getRateStore() {
		return rateStore;
	}

	public void setRateCache(ConcurrentHashMap<String, BitcoinRate> rateStore) {
		this.rateStore = rateStore;
	}

}
