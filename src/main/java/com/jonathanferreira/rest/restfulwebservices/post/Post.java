package com.jonathanferreira.rest.restfulwebservices.post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jonathanferreira.rest.restfulwebservices.user.User;

import javax.persistence.*;

@Entity
public class Post {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

    public Post() {
    }

    public Post(Long id, String message, User user) {
        this.id = id;
        this.message = message;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
