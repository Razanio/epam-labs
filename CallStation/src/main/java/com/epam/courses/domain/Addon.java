package com.epam.courses.domain;

public class Addon extends Entity{
    private String name;
    private Double cost;
    private Boolean payment;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Boolean getPayment() {
        return payment;
    }

    public void setPayment(Boolean payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return name + ".(" + cost + ')';
    }
}
