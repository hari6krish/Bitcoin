package com.bitcoin.console.util;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.json.JsonParseException;

import com.bitcoin.console.model.BitcoinRate;

public class JsonUtil {
	
	public static String mapCurrentRate(String data, String currency){
		String rate = null;
		try{
			JSONObject json = new JSONObject(data);        
			rate = json.getJSONObject("bpi").getJSONObject(currency).getString("rate");
		}catch(Exception e){
			throw new JsonParseException();
		}
		return rate;
	}
	
	public static BitcoinRate populateHistoricData(BitcoinRate bitcoinRate, String data,String currency){
		try{
			JSONObject json = new JSONObject(data).getJSONObject("bpi");
			BigDecimal lowest = BigDecimal.ZERO;
			BigDecimal highest = BigDecimal.ZERO;
			for(String key : json.keySet()){
				BigDecimal rateValue = json.getBigDecimal(key);
				if(rateValue.compareTo(lowest)<0 || lowest.compareTo(BigDecimal.ZERO)==0)
					lowest=rateValue;
				else if(rateValue.compareTo(highest)>0)
					highest=rateValue;
			}
			bitcoinRate.setHighestRate(highest.toPlainString());
			bitcoinRate.setLowestRate(lowest.toPlainString());
		}catch(Exception e){
			throw new JsonParseException();
		}
		
		return bitcoinRate;
	}

}
