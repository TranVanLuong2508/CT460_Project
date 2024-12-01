package com.example.workflow;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class announce_Cancel implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        System.out.println("**********************");
        System.out.println("*                    *");
        System.out.println("*---Canceled order---*");
        System.out.println("*                    *");
        System.out.println("**********************");
    }
}
