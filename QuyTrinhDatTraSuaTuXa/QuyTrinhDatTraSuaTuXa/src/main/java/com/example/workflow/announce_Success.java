package com.example.workflow;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class announce_Success implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) execution.getVariable("price,stock");
        Map<String, Object> result = resultList.get(0);
        Object price = result.get("price");
        Object stocks = result.get("stock");

        int PriceTea_Num = Integer.parseInt(String.valueOf(price));
        int Number_In_Stock = Integer.parseInt(String.valueOf(stocks));

        //Get information from User form
        String username = (String) execution.getVariable("username");
        //format date
        LocalDateTime createBillDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String formattedCreateBillDate = createBillDate.format(formatter);
        //end format date
        long quantity = (Long) execution.getVariable("number");
        String milkteatype =  (String) execution.getVariable("milkteatype");
        String paymentmethod = (String) execution.getVariable("paymentmethods");
        String size_customer = (String) execution.getVariable("size");
        String size = size_customer.toUpperCase();
        long totalPrice ;
        if(size.equalsIgnoreCase("L")) {
             totalPrice = quantity*PriceTea_Num + 7000;
        } else {
             totalPrice = quantity*PriceTea_Num;
        }

        System.out.println("*******************************************************");
        System.out.println("Your milk tea has been ordered successfully");
        System.out.println("Order information");
        System.out.println("Customer's Name: " + username);
        System.out.println("Milk tea type: " + milkteatype);
        System.out.println("Size: " + size);
        System.out.println("Number of order: " + quantity);
        System.out.println("Booking price: " + totalPrice + " VND");
        System.out.println("You have chosen a payment method: "+paymentmethod);
        System.out.println("Create bill at: " + formattedCreateBillDate);
    }
}
