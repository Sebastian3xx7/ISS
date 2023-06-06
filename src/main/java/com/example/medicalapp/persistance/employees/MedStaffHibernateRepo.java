package com.example.medicalapp.persistance.employees;


import com.example.medicalapp.domain.MedicalStaff;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import javax.persistence.Query;

public class MedStaffHibernateRepo implements MedStaffRepo{
    SessionFactory sessionFactory;

    public MedStaffHibernateRepo(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    //FIND USER
    @Override
    public MedicalStaff findBy(String username, String pass){
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();

                String hql = "FROM MedicalStaff WHERE username = :username AND password = :password";
                Query query = session.createQuery(hql, MedicalStaff.class);
                //List<User> logged= query.getResultList();
                //System.out.println(logged.size());
                //User usr=logged.get(0);

                org.hibernate.Query<MedicalStaff> query1 = session.createQuery("from MedicalStaff where username = :username and password = :password", MedicalStaff.class);
                query1.setParameter("username", username);
                query1.setParameter("password", pass);
                System.out.println("user:" + username);
                System.out.println("pass:" + pass);
                MedicalStaff usr1= query1.getSingleResult();




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
    public void save(MedicalStaff medicalStaff) {

    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public MedicalStaff findOne(Integer integer) {
        return null;
    }

    @Override
    public void update(Integer integer, MedicalStaff medicalStaff) {

    }

    @Override
    public Iterable<MedicalStaff> getAll() {
        return null;
    }


}

