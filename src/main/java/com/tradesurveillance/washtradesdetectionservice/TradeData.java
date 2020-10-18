package com.tradesurveillance.washtradesdetectionservice;

import java.time.LocalDate;
import java.time.LocalTime;

public class TradeData {

    Integer id;
    LocalDate date;
    LocalTime time;
    LocalTime orderTime;
    String traderId;
    String type;
    String securityId;
    String exchange;
    Integer quantity;
    Double pricePerUnit;
    String brokerId;
    Double brokeragePerUnit;
    Double netRatePerUnit;
    Double netTotal;

    TradeData(Integer id, LocalDate date, LocalTime time, LocalTime orderTime, String traderId, String type, String securityId, String exchange, Integer quantity, Double pricePerUnit, String brokerId, Double brokeragePerUnit, Double netRatePerUnit, Double netTotal)
    {
        this.id=id;
        this.date=date;
        this.time=time;
        this.orderTime=orderTime;
        this.traderId=traderId;
        this.type=type;
        this.securityId=securityId;
        this.exchange=exchange;
        this.quantity=quantity;
        this.pricePerUnit=pricePerUnit;
        this.brokerId=brokerId;
        this.brokeragePerUnit=brokeragePerUnit;
        this.netRatePerUnit=netRatePerUnit;
        this.netTotal=netTotal;
    }
}
