package com.example.workflow;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.List;
import java.util.Map;

public class announce_OutOfStock implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) execution.getVariable("price,stock");
        Map<String, Object> result = resultList.get(0);
        long quantity = (Long) execution.getVariable("number");
        Object stocks = result.get("stock");
        int Number_In_Stock = Integer.parseInt(String.valueOf(stocks));
        if(Number_In_Stock == 0) {
            System.out.println("******************");
            System.out.println("*                *");
            System.out.println("*--Out of Stock--*");
            System.out.println("*                *");
            System.out.println("******************");
        } else if( quantity > Number_In_Stock) {
            System.out.println("*****************************");
            System.out.println("*                           *");
            System.out.println("*--Number of Order Invalid--*");
            System.out.println("*                           *");
            System.out.println("*****************************");
        }
    }
}
