package com.test;

import java.math.BigDecimal;

public class TestForStr {
public static void main(String[] args) {

	
	
	double rate=Double.parseDouble("100.000000");
	double res=Double.parseDouble(String.valueOf(1000000.0000))*rate/100;
	BigDecimal usd = new BigDecimal(res);
	System.out.println(usd);
	BigDecimal usd_rate = new BigDecimal("14.238523");
	BigDecimal c=BigDecimal.valueOf(100000000.0000);
	System.out.println(c.divide(usd_rate));

	System.out.println("2020-11-21".replace("-", ""));
}
}
