package com.example.demo.message.service;

import com.example.demo.message.model.Message;

import java.util.List;

public interface MessageService {

    /**
     * @param message     message body
     * @param fromUserKey current user token in coockie
     * @param toUserEmail reciever user email selected by combo
     */
    Message addMessage(String message, String fromUserKey, String toUserEmail);

    /**
     * message to be deleted
     */
    void deleteMessage(Message message);

    /**
     * @return returns current user's incoming messages
     */
    List<Message> getAllIncomingMessagesByUserId(Integer userId);


    /**
     * @return returns current user's sent messages
     */
    List<Message> getAllSentMessagesByUserId(Integer userId);

    /**
     * @return returns all messages for admin only
     */
    List<Message> getAllMessages();
}
