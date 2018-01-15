package com.example.demo.message.controller;

import com.example.demo.message.model.Message;
import com.example.demo.message.service.MessageService;
import com.example.demo.user.model.User;
import com.example.demo.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    /**
     * @return if user doesn't have token return null
     * else return all incoming messages for user
     */
    @RequestMapping(value = "/getIncomingMessages", method = RequestMethod.GET)
    public List<Message> getIncomingMessagesForCurrentUser(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user") && cookie.getValue() != null) {
                    User user = userService.getUserByTokenKey(cookie.getValue());
                    return messageService.getAllIncomingMessagesByUserId(user.getId());
                }
            }
        }
        return null;
    }

    /**
     * @return if user doesn't have token return null
     * else return all sent messages for user
     */
    @RequestMapping(value = "/getSentMessages", method = RequestMethod.GET)
    public List<Message> getSentMessagesForCurrentUser(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user") && cookie.getValue() != null) {
                    User user = userService.getUserByTokenKey(cookie.getValue());
                    return messageService.getAllSentMessagesByUserId(user.getId());
                }
            }
        }
        return null;
    }

    /**
     * returns all messages for admin
     */
    @RequestMapping(value = "/getAllMessages", method = RequestMethod.GET)
    public List<Message> getAllMessages(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user") && cookie.getValue() != null) {
                    User user = userService.getUserByTokenKey(cookie.getValue());
                    if (user.getRole().getId().equals(1)) {
                        return messageService.getAllMessages();
                    }
                }
            }
        }
        return null;
    }

    /**
     * delete message( for admin)
     */
    @RequestMapping(value = "/deleteMessage", method = RequestMethod.POST)
    public void deleteMessage(@RequestBody Message message, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user") && cookie.getValue() != null) {
                    User user = userService.getUserByTokenKey(cookie.getValue());
                    if (user.getRole().getId().equals(1)) {
                        messageService.deleteMessage(message);
                    }
                }
            }
        }
    }


}
