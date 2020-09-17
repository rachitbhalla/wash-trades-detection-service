package com.tradesurveillance.washtradesdetectionservice;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class detectWashTrade
{
    //Checking Trade is within the restricted time period
    Boolean checkTime(LocalTime t1, LocalTime t2)
    {
        int a = t1.compareTo(t2);
        if(a <=5)
            return true;
        else
            return false;
    }

    //Checking trader and broker are same
    Boolean checkTrader(String traderId1, String traderId2,String brokerId1, String brokerId2)
    {
        if(traderId1.equals(traderId2) && brokerId1.equals(brokerId2))
            return true;
        else
            return false;
    }

    //Checking trade is same
    Boolean checkTrade(int id1,int id2,String type1,String type2,String securityId1,String securityId2)
    {
        if(id1!=0 || id2!=0)
            if(type1.compareTo(type2) !=0 )
                if(securityId1.equals(securityId2))
                    return true;
                else
                    return false;
            else
                return false;
        else
            return false;

    }

    //Checking trade price, quantity and brokerage is not zero
    Boolean checkTradePV(double pricePerUnit1, double pricePerUnit2, int quantity1,int quantity2, double brokeragePerUnit1,double brokeragePerUnit2 )
    {
        if(pricePerUnit1==pricePerUnit2 && quantity1==quantity2 && (brokeragePerUnit1!=0) && (brokeragePerUnit2!=0) )
            return true;
        else
            return false;

    }



    List washTrade(List trade)
    {
        List<TradeData> washTradelist = new ArrayList<>();
        //Iterator itr=trade.iterator();
        for(int i=0;i<trade.size();i++)
        {

            TradeData temp=(TradeData)trade.get(i);
            for(int j=i+1;j<trade.size();j++)
            {

                TradeData temp1=(TradeData)trade.get(j);
                if(checkTime(temp.orderTime,temp1.orderTime))
                    if (checkTrader(temp.traderId,temp1.traderId,temp.brokerId,temp1.brokerId))
                        if (checkTrade(temp.id,temp1.id,temp.type,temp1.type,temp.securityId,temp1.securityId))
                            if (checkTradePV(temp.pricePerUnit,temp1.pricePerUnit,temp.quantity,temp1.quantity,temp.brokeragePerUnit,temp1.brokeragePerUnit))
                            {
                                washTradelist.add(temp);
                                washTradelist.add(temp1);
                                temp.id=0;
                                temp1.id=0;
                            }

            }
        }

        return washTradelist;
    }

}
