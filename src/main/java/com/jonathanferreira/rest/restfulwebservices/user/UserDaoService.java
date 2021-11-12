package com.jonathanferreira.rest.restfulwebservices.user;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class UserDaoService {

    private static long userCount = 3;
    private static List<User> users = new ArrayList<>();



    static {
        users.add(new User(1L, "Jonathan", new Date()));
        users.add(new User(2L, "Eovan", new Date()));
        users.add(new User(3L, "Johan", new Date()));
        users.add(new User(4L, "Jon", new Date()));
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user){
        if(user.getId() == null){
            userCount += 1;
            user.setId(userCount);
            users.add(user);
        }{
            users.add(user);
        }
        return user;
    }

    public User findOne(long id){
        for(User user:users){
            if(user.getId() == id){
                return user;
            }
        }
        return null;
    }

}
