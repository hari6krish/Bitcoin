package com.bitcoin.console.services;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ConsoleResponseService implements CommandLineRunner {

	@Autowired
	private IBitcoinRateStoreService bitcoinRateStoreService;
	
	@Override
	public void run(String... args) throws Exception {
		bitcoinRateStoreService.populateBitCoinRates();
		System.out.println("Please entry the currency code to check Bitcoin rate");
		System.out.println("____________________________________________________");
		while(true){
			System.out.println("Currency Code:- ");
			Scanner scanner = new Scanner(System.in);
			String currency = scanner.nextLine();
			if(!bitcoinRateStoreService.getRateStore().containsKey(currency))
				System.out.println("Currency Not Supported. Please try some other Currency");
			else
			System.out.println(bitcoinRateStoreService.getRateStore().get(currency).toString());
		}
	}
}
