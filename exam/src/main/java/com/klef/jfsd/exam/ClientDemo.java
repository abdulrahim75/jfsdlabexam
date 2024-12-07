package com.klef.jfsd.exam;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class ClientDemo {
    public static void main(String[] args) {
        // Load configuration
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        cfg.addAnnotatedClass(Department.class);

        SessionFactory sessionFactory = cfg.buildSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            String hql = "UPDATE Department SET name = ?1, location = ?2 WHERE deptId = ?3";
            Query query = session.createQuery(hql);
            query.setParameter(1, "Sunny");
            query.setParameter(2, "London");
            query.setParameter(3, 1); 

            int result = query.executeUpdate();
            System.out.println(result + " record(s) updated.");

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
