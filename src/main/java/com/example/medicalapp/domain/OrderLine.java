package com.example.medicalapp.domain;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class OrderLine extends Entity<Integer>{

    private String medicine;
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderid")
    private int orderid;

    public OrderLine() {

    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }


    public OrderLine(Integer id, String idMedicine, Integer quantity, Integer orderid) {
        setId(id);
        this.medicine = idMedicine;
        this.quantity = quantity;
        this.orderid=orderid;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getOrder() {
        return orderid;
    }

    public void setOrder(Integer order) {
        this.orderid = order;
    }
}
