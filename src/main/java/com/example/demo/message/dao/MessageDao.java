package com.example.demo.message.dao;

import com.example.demo.common.dao.Dao;
import com.example.demo.common.dao.SessionFactoryHelper;
import com.example.demo.message.model.Message;
import com.example.demo.user.model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class MessageDao extends Dao<Message> {
    public MessageDao(SessionFactoryHelper sessionFactoryHelper) {
        super(sessionFactoryHelper);
    }

    public List<Message> getMessageByFromUserId(Integer id) {
        Session session = sessionFactoryHelper.getSessionFactory().openSession();
        try {
            Criteria criteria = session.createCriteria(Message.class)
                    .add(Restrictions.eq("fromUser.id", id));

            return (List<Message>) criteria.list();
        } finally {
            session.close();
        }
    }

    public List<Message> getMessageByToUserId(Integer id) {
        Session session = sessionFactoryHelper.getSessionFactory().openSession();
        try {
            Criteria criteria = session.createCriteria(Message.class)
                    .add(Restrictions.eq("toUser.id", id));
            return (List<Message>) criteria.list();
        } finally {
            session.close();
        }
    }
}
