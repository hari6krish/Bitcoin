package com.bitcoin.console.model;

public class BitcoinRate {
	
	private String currency;
	private String currentRate;
	private String lowestRate;
	private String highestRate;
	
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getCurrentRate() {
		return currentRate;
	}
	public void setCurrentRate(String currentRate) {
		this.currentRate = currentRate;
	}
	public String getLowestRate() {
		return lowestRate;
	}
	public void setLowestRate(String lowestRate) {
		this.lowestRate = lowestRate;
	}
	public String getHighestRate() {
		return highestRate;
	}
	public void setHighestRate(String highestRate) {
		this.highestRate = highestRate;
	}
	@Override
	public String toString() {
		return "Bitcoin rate for " + currency + " is " + currentRate + ", lowest and highest rate in last 30days are " + lowestRate
				+ " and " + highestRate;
	}
	
}
