package io.swagger.services;


import io.swagger.model.Survey;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class SurveyService {


    private static SessionFactory factory;

    public List<Survey> getAllSurveys(String EmailInput) {
        try {

            factory = new Configuration().configure().addAnnotatedClass(Survey.class).buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        Session session = factory.openSession();
        Transaction tx = null;


        List<Survey> Results = null;
        try {
            tx = session.beginTransaction();
            String query = "select * from SurvHey_DB.Survey where E_Mail = "+ EmailInput+" ;";
            Results = session.createQuery(query, Survey.class).list();


            tx.commit();
            session.close();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return Results;
    }







    public Survey getSurvey(long SurveyID_Input) {
        try {

            factory = new Configuration().configure().addAnnotatedClass(Survey.class).buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        Session session = factory.openSession();
        Transaction tx = null;


        Survey Result = null;
        try {
            tx = session.beginTransaction();
            String query = "select * from SurvHey_DB.Survey where Survey_ID = "+ SurveyID_Input+" ;";
            Result = session.createQuery(query, Survey.class).getSingleResult();


            tx.commit();
            session.close();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return Result;
    }


    public Survey createSurvey(Survey Survey_Input) {
        try {

            factory = new Configuration().configure().addAnnotatedClass(Survey.class).buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        Session session = factory.openSession();


        try {

            session.save(Survey_Input);

            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

     return Survey_Input;
    }


}
