package com.bitcoin.console.services;

import java.util.concurrent.ConcurrentHashMap;

import com.bitcoin.console.model.BitcoinRate;

public interface IBitcoinRateStoreService {
	
	public void populateBitCoinRates();
	
	public ConcurrentHashMap<String,BitcoinRate> getRateStore();

}
