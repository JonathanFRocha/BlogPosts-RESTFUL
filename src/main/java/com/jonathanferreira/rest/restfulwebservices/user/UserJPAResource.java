package com.jonathanferreira.rest.restfulwebservices.user;

import com.jonathanferreira.rest.restfulwebservices.post.Post;
import com.jonathanferreira.rest.restfulwebservices.post.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/jpa/users")
public class UserJPAResource {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

    @GetMapping("")
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public EntityModel<User> findById(@PathVariable long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isEmpty()){
            throw new UserNotFoundException("id - "+id);
        }
        var user = optionalUser.get();
        EntityModel<User> model = EntityModel.of(user);
        WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).findAll());
        model.add(linkToUsers.withRel("all-users"));
        return model;
    }

    @PostMapping("")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        var u = userRepository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(u.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable long id) {
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/posts")
    public List<Post> findAllPostsFromUserId(@PathVariable Long id) {
       var optionalUser = userRepository.findById(id);
        if(optionalUser.isEmpty()){
            throw new UserNotFoundException("id - "+id);
        }

        return optionalUser.get().getPosts();
    }

    @PostMapping("/{id}/posts")
    public ResponseEntity<Object> savePostFromUserId(@PathVariable Long id, @Valid @RequestBody Post post) {
        var optionalUser = userRepository.findById(id);
        if(optionalUser.isEmpty()){
            throw new UserNotFoundException("id - "+id);
        }
        var user = optionalUser.get();

        post.setUser(user);

        var a = postRepository.save(post);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}/posts")
                .buildAndExpand(a.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
