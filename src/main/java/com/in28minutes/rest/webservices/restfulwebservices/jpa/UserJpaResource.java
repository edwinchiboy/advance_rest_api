package com.in28minutes.rest.webservices.restfulwebservices.jpa;

import com.in28minutes.rest.webservices.restfulwebservices.post.Post;
import com.in28minutes.rest.webservices.restfulwebservices.post.PostRepository;
import com.in28minutes.rest.webservices.restfulwebservices.user.User;
import com.in28minutes.rest.webservices.restfulwebservices.user.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJpaResource {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public UserJpaResource(final PostRepository postRepository, final UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();

    }

    @GetMapping("/jpa/users/{userId}")
    public EntityModel<User> retrieveUser(@PathVariable("userId") final int userId) {
        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty())
            throw new UserNotFoundException("id:" + userId);

        EntityModel<User> entityModel = EntityModel.of(user.get());

        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());

        entityModel.add(link.withRel("all-users"));

        return entityModel;
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {

        User savedUser = userRepository.save(user);

        final URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedUser.getId())
            .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/jpa/users/{userId}")
    public void deleteUser(@PathVariable("userId") final int userId) {
        userRepository.deleteById(userId);

    }

    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Object> createPostForUser(@PathVariable int id, @Valid @RequestBody Post post) {

        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty())
            throw new UserNotFoundException("id:" + id);
        post.setUser(user.get());

        Post savedPost = postRepository.save(post);

        final URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedPost.getId())
            .toUri();
        return ResponseEntity.created(location).build();



    }

}
