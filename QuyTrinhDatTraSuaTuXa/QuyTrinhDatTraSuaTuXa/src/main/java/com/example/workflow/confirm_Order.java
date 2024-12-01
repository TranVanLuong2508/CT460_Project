package com.example.workflow;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class confirm_Order implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String confirm = (String) execution.getVariable("confirm_order");
        if(confirm.equals("OK")) {
            execution.setVariable("check", 1);
        } else {
            execution.setVariable("check", 0);
        }
    }
}
