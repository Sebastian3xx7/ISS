package com.example.medicalapp.persistance.orders;

import com.example.medicalapp.domain.Order;
import com.example.medicalapp.domain.OrderLine;
import com.example.medicalapp.persistance.Repository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class OrderLineHibernateRepo implements Repository<Integer, OrderLine> {

    SessionFactory sessionFactory;

    public OrderLineHibernateRepo(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(OrderLine orderLine) {
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.save(orderLine);
                tx.commit();
            } catch (RuntimeException ex) {
                System.err.println("Eroare la insert "+ex);
                if (tx != null)
                    tx.rollback();
            }
        }
    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public OrderLine findOne(Integer integer) {
        return null;
    }

    @Override
    public void update(Integer integer, OrderLine orderLine) {

    }

    public List<OrderLine> getOrderlinesForOrder(Integer id){
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();

                org.hibernate.Query<OrderLine> query1 = session.createQuery("from OrderLine where orderid=: id", OrderLine.class);
                query1.setParameter("id", id);
                List<OrderLine> usr1= query1.getResultList();

                tx.commit();
                return usr1;
            } catch (RuntimeException ex) {
                ex.printStackTrace();
                System.err.println("Eroare la select "+ex);
                if (tx != null)
                    tx.rollback();
            }
        }
        return null;

    }

    @Override
    public Iterable<OrderLine> getAll() {
        return null;
    }
}
