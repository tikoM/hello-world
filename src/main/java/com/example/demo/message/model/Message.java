package com.example.demo.message.model;

import com.example.demo.user.model.User;

import javax.persistence.*;

@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "messageId")
    private Integer id;

    @Column(name = "text")
    private String message;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "toUserId")
    private User toUser;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fromUserId")
    private User fromUser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }
}
