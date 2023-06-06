package com.example.medicalapp.persistance.employees;

import com.example.medicalapp.domain.Pharmacist;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import javax.persistence.Query;
import java.util.List;

public class PharmacistHibernateRepo implements PharmacistRepo{
    SessionFactory sessionFactory;
    public PharmacistHibernateRepo(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    //FIND USER
    @Override
    public Pharmacist findBy(String username, String pass){
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();

                String hql = "FROM Pharmacist WHERE username = :username AND password = :password";
                Query query = session.createQuery(hql, Pharmacist.class);
                //List<User> logged= query.getResultList();
                //System.out.println(logged.size());
                //User usr=logged.get(0);

                org.hibernate.Query<Pharmacist> query1 = session.createQuery("from Pharmacist where username = :username and password = :password", Pharmacist.class);
                query1.setParameter("username", username);
                query1.setParameter("password", pass);
                System.out.println("user:" + username);
                System.out.println("pass:" + pass);
                Pharmacist usr1= query1.getSingleResult();




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
    public void save(Pharmacist phrm) {

    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public Pharmacist findOne(Integer id) {
        return null;
    }

    @Override
    public void update(Integer integer, Pharmacist phrm) {

    }

    @Override
    public List<Pharmacist> getAll() {
        return null;
    }


}


