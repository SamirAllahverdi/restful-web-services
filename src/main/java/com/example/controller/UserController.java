package com.example.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.example.exception.PostNotFoundException;
import com.example.model.Post;
import com.example.model.User;
import com.example.service.UserService;
import com.example.exception.UserNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@Log4j2
@AllArgsConstructor
public class UserController {

    private final UserService service;

    /**
     * - Users -
     */
    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id) {

        User user = service.findOne(id).orElseThrow(() -> new UserNotFoundException("id = " + id));

        //HATEOAS
        EntityModel<User> resource = new EntityModel<>(user);
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        resource.add(linkTo.withRel("all-users"));

        return resource;
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User savedUser = service.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();

    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {

        if (service.exists(id)) service.deleteById(id);
        else throw new UserNotFoundException("id = " + id);

    }

    /**
     * - Posts -
     */
    @GetMapping("/users/{id}/posts")
    public Collection<Post> retrieveUserPosts(@PathVariable int id) {
        User user = service.findOne(id).orElseThrow(() -> new UserNotFoundException("id -" + id));
        return user.getPosts();
    }


    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Object> createUserPost(@PathVariable int id, @RequestBody Post post) {
        User user = service.findOne(id).orElseThrow(() -> new UserNotFoundException("id -" + id));
        user.getPosts().add(post);
        service.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}/posts").buildAndExpand(post.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{user_id}/posts/{post_id}")
    public void deleteUserPost(@PathVariable int user_id, @PathVariable int post_id) {
        User user = service.findOne(user_id).orElseThrow(() -> new UserNotFoundException("id -" + user_id));

        Post post = user.getPosts().stream()
                .filter(a -> a.getId() == post_id)
                .findFirst()
                .orElseThrow(() -> new PostNotFoundException("user id = " + user_id + ", post id -  " + post_id));

        user.getPosts().remove(post);
        service.save(user);
    }
}
