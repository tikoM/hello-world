package com.example.demo.user.dao;

import com.example.demo.common.dao.Dao;
import com.example.demo.common.dao.SessionFactoryHelper;
import com.example.demo.user.model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;


public class UserDao extends Dao<User> {
    public UserDao(SessionFactoryHelper sessionFactoryHelper) {
        super(sessionFactoryHelper);
    }

    public User getUserByEmail(String email) {
        User user = null;
        try (Session session = sessionFactoryHelper.getSessionFactory().openSession()) {
            Criteria criteria = session.createCriteria(User.class)
                    .add(Restrictions.eq("email", email));
            Object result = criteria.uniqueResult();
            if (result != null) {
                user = (User) result;
            }
        }
        return user;
    }

    public User getUserByToken(String token) {
        User user = null;
        try (Session session = sessionFactoryHelper.getSessionFactory().openSession()) {
            Criteria criteria = session.createCriteria(User.class)
                    .add(Restrictions.eq("incrementedKey", token));

            Object result = criteria.uniqueResult();
            if (result != null) {
                user = (User) result;
            }
        }
        return user;
    }

    public User getUserById(Integer id) {
        User user = null;
        try (Session session = sessionFactoryHelper.getSessionFactory().openSession()) {
            Criteria criteria = session.createCriteria(User.class)
                    .add(Restrictions.eq("id", id));

            Object result = criteria.uniqueResult();
            if (result != null) {
                user = (User) result;
            }
        }
        return user;
    }
}
