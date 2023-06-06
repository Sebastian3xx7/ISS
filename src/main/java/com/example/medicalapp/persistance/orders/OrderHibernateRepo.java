package com.example.medicalapp.persistance.orders;


import com.example.medicalapp.domain.MedicalStaff;
import com.example.medicalapp.domain.Order;
import com.example.medicalapp.persistance.Repository;
import com.example.medicalapp.persistance.employees.MedStaffRepo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

public class OrderHibernateRepo implements Repository<Integer, Order> {
    SessionFactory sessionFactory;

    public OrderHibernateRepo(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Order order) {
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.save(order);
                tx.commit();
            } catch (RuntimeException ex) {
                System.err.println("Eroare la insert "+ex);
                if (tx != null)
                    tx.rollback();
            }
        }
    }

    @Override
    public void delete(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();

                org.hibernate.query.Query<Order> query = session.createQuery("DELETE FROM Order WHERE id = :id");
                query.setParameter("id", id);
                int rowsAffected = query.executeUpdate();
                System.out.println(rowsAffected);
                tx.commit();
            } catch (RuntimeException ex) {
                System.err.println("Error during deletion: " + ex);
                if (tx != null)
                    tx.rollback();
            }
        }
    }


    @Override
    public Order findOne(Integer id) {
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();

                org.hibernate.Query<Order> query1 = session.createQuery("from Order where id=: id", Order.class);
                query1.setParameter("id", id);
                Order usr1= query1.getSingleResult();

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


    public List<Order> findForUs(Integer id) {
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();

                org.hibernate.Query<Order> query1 = session.createQuery("from Order where userid=: id", Order.class);
                query1.setParameter("id", id);
                List<Order> usr1= query1.getResultList();

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
    public void update(Integer integer, Order order) {

    }

    @Override
    public List<Order> getAll() {
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                List<Order> orders =
                        session.createQuery("from Order", Order.class).list();
                tx.commit();
                return orders;

            } catch (RuntimeException ex) {
                System.err.println("Eroare la select "+ex);
                if (tx != null)
                    tx.rollback();
            }
        }
        return null;
    }



}


