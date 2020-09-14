package com.tradesurveillance.washtradesdetectionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WashTradesDetectionServiceApplication {
	Node head;  
  
    static class Node { 
  
        int clientId;
        String time;
        String tradeType;
        String securityType;
        String ISIN;
        int price;
        int volume;
        Node next; 
  
        Node(int clientId, String time, String tradeType, String securityType, String ISIN, int price, int volume) 
        { 
            this.clientId=clientId;
            this.time=time;
            this.tradeType=tradeType;
            this.securityType=securityType;
            this.ISIN=ISIN;
            this.price=price;
            this.volume=volume;
            this.next = null; 
        } 
    } 
     //checking trade parameters 
public static	boolean checkTrade(int clientId1, int clientId2, String tradeType1, String tradeType2, String securityType1, String securityType2, String ISIN1,String ISIN2)
  {
    if (clientId1 == clientId2)
    {
      if( securityType1.equals(securityType2))
      {
		if(ISIN1.equals(ISIN2))
		{ 
	
         if(tradeType1.equals(tradeType2))
            return false;
         else
           return true;
		}
		else
			return false;
      }
     else 
       return false;
    }
    else 
       return false;

  }
 //checking time, price, volume parameter of trade
 public static boolean checkTime(String time1,String time2,int price1, int price2,int volume1,int volume2)
  {
        Timestamp ts1 = Timestamp.valueOf(time1);
        Timestamp ts2 = Timestamp.valueOf(time2);
        int a = ts1.compareTo(ts2);
        if(a <=5 )
        {
            if (price1==price2)
            {
              if( volume1==volume2)
                      return true;
              else
                    return false;
            }
	    else 
                return false;
        }
        else            
           return false;        

  }
	  
    // Method to insert a new node in list
     public static WashTradesDetectionServiceApplication insert(WashTradesDetectionServiceApplication list, int clientId, String time, String tradeType, String securityType, String ISIN, int price, int volume) 
     { 
        Node new_node = new Node( clientId,  time, tradeType,  securityType,  ISIN,  price,  volume); 
        new_node.next = null; 
           
        if (list.head == null) { 
            list.head = new_node; 
        } 
        else { 
            Node last = list.head; 
            while (last.next != null) { 
                last = last.next; 
            } 
               
            last.next = new_node; 
        }          
        return list; 
    } 
  
    
/* Function detectWashTrade returns a list containing all the wash trades detected.
	Input: List of all the Trades(list)
	Output: List containing all the wash trades(washTrade)*/
    public static WashTradesDetectionServiceApplication detectWashTrade(WashTradesDetectionServiceApplication list)
    { 
        
		Node currNode = list.head;
		WashTradesDetectionServiceApplication washTrade=new WashTradesDetectionServiceApplication ();
		while(currNode !=null)
		{
		 	Node nextNode=currNode.next;
		 	Node pre_nextNode=nextNode;
         		Boolean flag;
		
         		while (nextNode != null )  {
		 	if (checkTime(currNode.time,nextNode.time,currNode.price,nextNode.price,currNode.volume,nextNode.volume))
			{
			  	flag=checkTrade(currNode.clientId,nextNode.clientId,currNode.tradeType,nextNode.tradeType,currNode.securityType,nextNode.securityType,currNode.ISIN,nextNode.ISIN);
			// If trade wash is detected, it is inserted into a list washTrade which contains all the lists of wash trades. 
			 	if (flag==true)
				{ 
					washTrade=insert(washTrade,currNode.clientId,currNode.time,currNode.tradeType,currNode.securityType,currNode.ISIN,currNode.price,currNode.volume);
					washTrade=insert(washTrade,nextNode.clientId,nextNode.time,nextNode.tradeType,nextNode.securityType,nextNode.ISIN,nextNode.price,nextNode.volume);
					pre_nextNode.next=nextNode.next;
                		}
			}	
				pre_nextNode=nextNode;
				 nextNode = nextNode.next; 
		}      
         	currNode=currNode.next;
         }
         	return washTrade;
	 }
     	   


	public static void main(String[] args) {
		SpringApplication.run(WashTradesDetectionServiceApplication.class, args);
		WashTradesDetectionServiceApplication list = new WashTradesDetectionServiceApplication (); 
         WashTradesDetectionServiceApplication wash = new WashTradesDetectionServiceApplication ();
        wash =detectWashTrade(list);
	}

}
