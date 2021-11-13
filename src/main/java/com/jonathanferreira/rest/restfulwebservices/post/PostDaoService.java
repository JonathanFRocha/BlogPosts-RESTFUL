package com.jonathanferreira.rest.restfulwebservices.post;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PostDaoService {
    static private long postCount = 4;
    static private List<Post> postList = new ArrayList<>();

    static {
        postList.add(new Post(1L, 1L, "post 1"));
        postList.add(new Post(2L, 2L, "post 2"));
        postList.add(new Post(3L, 2L, "post 3"));
        postList.add(new Post(4L, 3L, "post 4"));
    }

    public List<Post> findAll () {
        return postList;
    }

    public Post save (Post post) {
        if (post.getId() == null) {
            postCount++;
            post.setId(postCount);
            postList.add(post);
        }
        postList.add(post);
        return post;
    }
}
