package org.acme;

import io.javaoperatorsdk.operator.api.ObservedGenerationAwareStatus;

public class PizzaOperatorStatus extends ObservedGenerationAwareStatus {

    private String message;

    public PizzaOperatorStatus() {
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

}
