package com.example.demo.message.service;

import com.example.demo.message.dao.MessageDao;
import com.example.demo.message.model.Message;
import com.example.demo.user.service.UserService;

import java.util.List;

public class MessageServiceImpl implements MessageService {
    private final MessageDao messageDao;
    private final UserService userService;

    public MessageServiceImpl(MessageDao messageDao, UserService userService) {
        this.messageDao = messageDao;
        this.userService = userService;
    }


    @Override
    public Message addMessage(String messageString, String fromUserKey, String toUserEmail) {
        Message message = new Message();
        message.setFromUser(userService.getUserByTokenKey(fromUserKey));
        message.setToUser(userService.getUserByEmail(toUserEmail));
        message.setMessage(messageString);
        return messageDao.create(message);
    }

    @Override
    public void deleteMessage(Message message) {
        messageDao.delete(message);
    }

    @Override
    public List<Message> getAllIncomingMessagesByUserId(Integer userId) {
        return messageDao.getMessageByToUserId(userId);
    }

    @Override
    public List<Message> getAllSentMessagesByUserId(Integer userId) {
        return messageDao.getMessageByFromUserId(userId);
    }

    @Override
    public List<Message> getAllMessages() {
        return messageDao.getAll(Message.class);
    }
}
