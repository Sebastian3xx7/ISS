package com.example.medicalapp.utils.events;


import com.example.medicalapp.domain.Order;

public class OrderChangeEntityEvent implements Event {
    private com.example.medicalapp.utils.events.ChangeEventType type;
    private Order data, oldData;

    public OrderChangeEntityEvent(com.example.medicalapp.utils.events.ChangeEventType type, Order data) {
        this.type = type;
        this.data = data;
    }
    public OrderChangeEntityEvent(com.example.medicalapp.utils.events.ChangeEventType type, Order data, Order oldData) {
        this.type = type;
        this.data = data;
        this.oldData=oldData;
    }

    public com.example.medicalapp.utils.events.ChangeEventType getType() {
        return type;
    }

    public Order getData() {
        return data;
    }
//
//    public User getOldData() {
//        return oldData;
//    }
}