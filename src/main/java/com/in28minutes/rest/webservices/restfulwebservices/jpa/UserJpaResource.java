package com.in28minutes.rest.webservices.restfulwebservices.jpa;

import com.in28minutes.rest.webservices.restfulwebservices.user.User;
import com.in28minutes.rest.webservices.restfulwebservices.user.UserDaoService;
import com.in28minutes.rest.webservices.restfulwebservices.user.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJpaResource {
    private final UserDaoService userDaoService;
    private  final UserRepository userRepository;

    public UserJpaResource(final UserDaoService userDaoService, final UserRepository userRepository) {
        this.userDaoService = userDaoService;
        this.userRepository = userRepository;
    }

    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers() {
        return userDaoService.findAll();

    }

    @GetMapping("/jpa/users/{userId}")
    public EntityModel<User> retrieveUser(@PathVariable("userId") final int userId) {
        User user = userDaoService.findOne(userId);

        if (user == null)
            throw new UserNotFoundException("id:" + userId);

        EntityModel<User> entityModel = EntityModel.of(user);

        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());

        entityModel.add(link.withRel("all-users"));

        return entityModel;
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {

        User savedUser = userDaoService.save(user);

        final URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedUser.getId())
            .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/jpa/users/{userId}")
    public void deleteUser(@PathVariable("userId") final int userId) {
        userDaoService.deleteById(userId);

    }

}
