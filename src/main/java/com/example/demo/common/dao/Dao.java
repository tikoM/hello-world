package com.example.demo.common.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public abstract class Dao<T> {

    protected SessionFactoryHelper sessionFactoryHelper;

    public Dao(SessionFactoryHelper sessionFactoryHelper) {
        this.sessionFactoryHelper = sessionFactoryHelper;
    }

    /**
     * default create method
     *
     * @param object - object to be created(type is due to to extending class)
     */
    public T create(T object) {
        Session session = sessionFactoryHelper.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        try {
            session.save(object);
            tr.commit();
        } catch (Exception runtimeException) {
            tr.rollback();
            throw runtimeException;
        } finally {
            session.close();
        }
        return object;
    }

    /**
     * default update method
     *
     * @param object - object to be updated(type is due to to extending class)
     */
    public T update(T object) {
        Session session = sessionFactoryHelper.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        try {
            session.update(object);
            tr.commit();
        } catch (RuntimeException runtimeException) {
            tr.rollback();
            throw runtimeException;
        } finally {
            session.close();
        }
        return object;
    }

    /**
     * default delete method
     *
     * @param object - object to be deleted(type is due to to extending class)
     */
    public void delete(T object) {
        Session session = sessionFactoryHelper.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        try {
            session.delete(object);
            tr.commit();
        } catch (RuntimeException runtimeException) {
            tr.rollback();
            throw runtimeException;
        } finally {
            session.close();
        }
    }


    /**
     * default get all
     *
     * @param className - class of object for which list is needed
     */
    public List<T> getAll(Class className) {
        Session session = sessionFactoryHelper.getSessionFactory().openSession();
        List<T> list = session.createCriteria(className).list();
        session.close();
        return list;
    }
}
