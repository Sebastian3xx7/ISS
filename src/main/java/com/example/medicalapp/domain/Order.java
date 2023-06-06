package com.example.medicalapp.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@javax.persistence.Entity
@Table( name = "Orders")
public class Order extends Entity<Integer>{
    private String date;
    private String description;
    private Integer userid;


    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderLine> orderLines=new ArrayList<>();


    public Order(String date, String desc, Integer userid) {

        this.description=desc;
        this.date=date;
        this.userid=userid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Order() {

    }

    public void addOrderLine(OrderLine orderLine) {
        orderLines.add(orderLine);
    }

    public void setOrderLines(List<OrderLine> lns){
        for(OrderLine orl:lns){
            orderLines.add(orl);
        }
    }

    public List<OrderLine> getOrderLines(){
        return orderLines;
    }

    public void setId(Integer id){
        super.setId(id);
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getIdOrder(){
        return getId();
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
