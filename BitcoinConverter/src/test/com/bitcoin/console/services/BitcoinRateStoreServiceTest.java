package com.bitcoin.console.services;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;

@SpringBootTest
public class BitcoinRateStoreServiceTest {
	
	@Autowired
	private IBitcoinRateStoreService bitcoinRateStoreService;
	
	@InjectMocks 
	private ICoinBaseDataLoadService coinBaseDataLoadService =  new CoinBaseDataLoadService(new RestTemplateBuilder());
	

	@Test
	public void populateBitCoinRatesTest(){
		Mockito.when(coinBaseDataLoadService.getCurrentBitCoinRateForCurrency(Mockito.anyString())).thenReturn(null);
		Mockito.when(coinBaseDataLoadService.getBitCoinHistoricalDataForCurrency(Mockito.anyString(),Mockito.anyString(),Mockito.anyString())).thenReturn(null);
		bitcoinRateStoreService.populateBitCoinRates();
		Assert.assertTrue(bitcoinRateStoreService.getRateStore().size()==0);
	}
}
