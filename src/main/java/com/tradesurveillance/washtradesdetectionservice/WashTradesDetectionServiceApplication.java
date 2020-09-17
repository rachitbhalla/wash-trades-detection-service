package com.tradesurveillance.washtradesdetectionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class WashTradesDetectionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WashTradesDetectionServiceApplication.class, args);
		List<TradeData> trade = new ArrayList<>();
		List<TradeData> washTradeL = new ArrayList<>();
		detectWashTrade a=new detectWashTrade();
		washTradeL=a.washTrade(trade);
	}

}
