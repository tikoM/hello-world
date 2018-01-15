package com.example.demo.message.dao;

import com.example.demo.common.dao.Dao;
import com.example.demo.common.dao.SessionFactoryHelper;
import com.example.demo.message.model.Message;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class MessageDao extends Dao<Message> {
    public MessageDao(SessionFactoryHelper sessionFactoryHelper) {
        super(sessionFactoryHelper);
    }

    public List<Message> getMessageByFromUserId(Integer id) {
        try (Session session = sessionFactoryHelper.getSessionFactory().openSession()) {
            Criteria criteria = session.createCriteria(Message.class)
                    .add(Restrictions.eq("fromUser.id", id));

            return (List<Message>) criteria.list();
        }
    }

    public List<Message> getMessageByToUserId(Integer id) {
        try (Session session = sessionFactoryHelper.getSessionFactory().openSession()) {
            Criteria criteria = session.createCriteria(Message.class)
                    .add(Restrictions.eq("toUser.id", id));
            return (List<Message>) criteria.list();
        }
    }
}
