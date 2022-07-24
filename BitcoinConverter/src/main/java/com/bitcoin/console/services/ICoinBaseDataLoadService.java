package com.bitcoin.console.services;

public interface ICoinBaseDataLoadService {
	
	public String getBitCoinHistoricalDataForCurrency(String startDate, String endDate, String currency);
	public String getCurrentBitCoinRateForCurrency(String currency);

}
