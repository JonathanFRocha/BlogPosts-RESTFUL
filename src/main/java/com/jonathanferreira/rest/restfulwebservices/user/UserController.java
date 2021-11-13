package com.jonathanferreira.rest.restfulwebservices.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserDaoService userDaoService;

    @GetMapping("")
    public List<User> findAll() {
        return userDaoService.findAll();
    }

    @GetMapping("/{id}")
    public EntityModel<User> findById(@PathVariable long id) {
        User user = userDaoService.findOne(id);
        if(user == null){
            throw new UserNotFoundException("id - "+id);
        }
        EntityModel<User> model = EntityModel.of(user);
        WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).findAll());
        model.add(linkToUsers.withRel("all-users"));
        return model;
    }

    @PostMapping("")
    public ResponseEntity createUser(@Valid @RequestBody User user) {
        var u = userDaoService.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(u.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable long id) {
        var user = userDaoService.delete(id);
        if(user == null ){
            throw new UserNotFoundException("id - " + id);
        }
        return ResponseEntity.noContent().build();
    }
}
