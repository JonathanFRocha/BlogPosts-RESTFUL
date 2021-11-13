package com.jonathanferreira.rest.restfulwebservices.user;

import com.jonathanferreira.rest.restfulwebservices.post.Post;

import javax.persistence.*;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    @Size(min = 2, max = 100, message = "Name should have at least 2 characters and have less than 100 characters")
    private String name;

    @Column
    @Past
    private Date birthDate;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    public User(){

    }

    public User(Long id, String name, Date birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

}
