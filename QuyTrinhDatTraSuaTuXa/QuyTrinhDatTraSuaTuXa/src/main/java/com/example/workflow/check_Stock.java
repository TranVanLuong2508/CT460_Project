package com.example.workflow;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class check_Stock implements JavaDelegate {
    @Override
    public void  execute (DelegateExecution execution) throws  Exception {
        //import map to get data from DMN
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) execution.getVariable("price,stock");
        Map<String, Object> result = resultList.get(0);
        Object price = result.get("price");
        Object stocks = result.get("stock");

        int PriceTea_Num = Integer.parseInt(String.valueOf(price));
        int Number_In_Stock = Integer.parseInt(String.valueOf(stocks));

        //Get information from User form
        String username = (String) execution.getVariable("username");
                       //format date
        LocalDateTime bookingDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String formattedDateTime = bookingDate.format(formatter);
                       //end format date
        long quantity = (Long) execution.getVariable("number");
        String milkteatype =  (String) execution.getVariable("milkteatype");
        String size_customer = (String) execution.getVariable("size");
        String size = size_customer.toUpperCase();
        long totalPrice = 0;
        if(size.equalsIgnoreCase("L")) {
            totalPrice = quantity*PriceTea_Num + 7000;
        } else if (size.equalsIgnoreCase("M")) {
            totalPrice = quantity*PriceTea_Num;
        }
        if((Number_In_Stock < quantity) || Number_In_Stock == 0) {
            execution.setVariable("validation", 0);
        } else {
            execution.setVariable("validation", 1);
            System.out.println("Welcome " + username + " to our Milk tea Store !");
            System.out.println("You have selected this milk tea type: " + milkteatype);
            System.out.println("Number of Order: " + quantity);
            System.out.println("Size: " + size);
            System.out.println("Total price: " + totalPrice + " VND");
            System.out.println("Order Date: " + formattedDateTime);
        }
    }
}
