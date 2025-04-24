package com.accountingAPI.accountingSoftware.model;

public class RatioResult {
    private double value;
    private String status; // Will be "Green", "Yellow", or "Red"

    public RatioResult(double v, String s) {
        value = v;
        status = s;
    }

    // Getters and setters

    public double getValue() {
        return value;
    }

    public void setValue(double v) {
        value = v;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String s) {
        status = s;
    }
}
