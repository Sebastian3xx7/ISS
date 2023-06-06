package com.example.medicalapp.persistance.pharmacy;

import com.example.medicalapp.domain.Order;
import com.example.medicalapp.domain.Stock;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public class StockHibernateRepo implements StockRepo {

    SessionFactory sessionFactory;

    public StockHibernateRepo(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public void save(Stock stock) {

    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public Stock findOne(Integer integer) {
        return null;
    }

    @Override
    public void update(Integer id, Stock stock) {
        try(Session session = sessionFactory.openSession()){
            Transaction tx=null;
            try{
                tx = session.beginTransaction();
                Stock stockx = session.load( Stock.class, id );

                // Message messageNou = session.load( Message.class, new Long(45) );
                stockx.setMedicine(stock.getMedicine());
                stockx.setQuantity(stock.getQuantity());

                //session.update(message);
                tx.commit();

            } catch(RuntimeException ex){
                System.err.println("Eroare la update "+ex);
                if (tx!=null)
                    tx.rollback();
            }
        }
    }

    @Override
    public void updateByFields(String medicine, Integer quan) {
        try(Session session = sessionFactory.openSession()){
            Transaction tx=null;
            try{
                tx = session.beginTransaction();
                String hql = "UPDATE Stock SET quantity = :newValue WHERE medicine = :med";
                Query query = session.createQuery(hql);
                query.setParameter("newValue", quan);
                query.setParameter("med", medicine);
                int rowsAffected = query.executeUpdate();
                System.out.println(rowsAffected);
                tx.commit();

            } catch(RuntimeException ex){
                System.err.println("Eroare la update "+ex);
                if (tx!=null)
                    tx.rollback();
            }
        }
    }

    public void updateByDecreasing(String medicine, Integer quan) {
        try(Session session = sessionFactory.openSession()){
            Transaction tx=null;
            try{
                tx = session.beginTransaction();
                String hql = "UPDATE Stock SET quantity = quantity- :newValue WHERE medicine = :med";
                Query query = session.createQuery(hql);
                query.setParameter("newValue", quan);
                query.setParameter("med", medicine);
                int rowsAffected = query.executeUpdate();
                System.out.println(rowsAffected);
                tx.commit();

            } catch(RuntimeException ex){
                System.err.println("Eroare la update "+ex);
                if (tx!=null)
                    tx.rollback();
            }
        }
    }

    @Override
    public Stock findByMed(String med) {
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();

                org.hibernate.Query<Stock> query1 = session.createQuery("from Stock where medicine=: med", Stock.class);
                query1.setParameter("med", med);
                Stock usr1= query1.getSingleResult();

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
    public List<Stock> getAll() {
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                List<Stock> stock =
                        session.createQuery("from Stock", Stock.class).list();
                tx.commit();
                return stock;

            } catch (RuntimeException ex) {
                System.err.println("Eroare la select "+ex);
                if (tx != null)
                    tx.rollback();
            }
        }
        return null;
    }
}
