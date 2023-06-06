package com.example.medicalapp.services;

import com.example.medicalapp.domain.*;
import com.example.medicalapp.persistance.employees.MedStaffRepo;
import com.example.medicalapp.persistance.employees.PharmacistRepo;
import com.example.medicalapp.persistance.orders.OrderHibernateRepo;
import com.example.medicalapp.persistance.orders.OrderLineHibernateRepo;
import com.example.medicalapp.persistance.pharmacy.StockRepo;
import com.example.medicalapp.utils.events.OrderChangeEntityEvent;
import com.example.medicalapp.utils.observer.Observable;
import com.example.medicalapp.utils.observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class Service implements Observable<OrderChangeEntityEvent> {

    MedStaffRepo medrepo;
    OrderHibernateRepo ordrepo;
    OrderLineHibernateRepo ordlrepo;
    PharmacistRepo phrmrepo;
    StockRepo strepo;

    private List<Observer<OrderChangeEntityEvent>> observers = new ArrayList<>();


    public Service(MedStaffRepo medrepo, OrderHibernateRepo or, OrderLineHibernateRepo orl, PharmacistRepo ph, StockRepo st) {

        this.medrepo = medrepo;
        this.ordrepo=or;
        this.ordlrepo=orl;
        this.phrmrepo=ph;
        this.strepo=st;
    }

    @Override
    public void addObserver(Observer<OrderChangeEntityEvent> e) {
        observers.add(e);

    }

    @Override
    public void removeObserver(Observer<OrderChangeEntityEvent> e) {
        observers.remove(e);
    }

    @Override
    public void notifyObservers(OrderChangeEntityEvent t) {
        observers.stream().forEach(x -> x.update(t));
    }


    public MedicalStaff getMed(String username, String password){

        return medrepo.findBy(username, password);
    }

    public void sendOrder(Order order){
        ordrepo.save(order);
        List<OrderLine> lines= order.getOrderLines();
        for(OrderLine orl:lines){
            ordlrepo.save(orl);
        }

    }

    public Order findOrder(Integer id){
        return ordrepo.findOne(id);
    }

    public List<OrderLine> getOrderlinesForOrder(Integer id){
//        Order ord=findOrder(id);
//        System.out.println(ord.getDescription());
        return ordlrepo.getOrderlinesForOrder(id);
    }

    public void addToOrder(OrderLine ordln, Order ord){
        ord.addOrderLine(ordln);
    }

    public void addOrder(Order order){
        ordrepo.save(order);
    }

    public List<Stock> getAllStock() {
        return (List<Stock>) strepo.getAll();
    }

    public Stock getStockForMed(String med){
        return strepo.findByMed(med);
    }

    public List<Order> getAllOrders() {
        return ordrepo.getAll();
    }

    public void updateStock(String med, Integer quan) {
        strepo.updateByFields(med, quan);
    }

    public void updateStockDec(String med, Integer quan) {
        strepo.updateByDecreasing(med, quan);
    }

    public boolean completeOrder(Order ord) {
        Integer id=ord.getIdOrder();
        List<OrderLine> allReqs=getOrderlinesForOrder(id);
        for(OrderLine ln:allReqs){
            if(ln.getQuantity()>getStockForMed(ln.getMedicine()).getQuantity()){
                return false;
            }
        }
        for(OrderLine ln:allReqs){
            updateStockDec(ln.getMedicine(), ln.getQuantity());
        }
        System.out.println(id);
        ordrepo.delete(id);
        return true;
    }

    public Pharmacist getPharmacist(String username, String password) {
        return phrmrepo.findBy(username, password);
    }

    public List<Order> getOrdersForUser(Integer idEm) {
        return ordrepo.findForUs(idEm);
    }
}
