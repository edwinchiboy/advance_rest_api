package com.in28minutes.rest.webservices.restfulwebservices.user;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserResource {
    private final UserDaoService userDaoService;

    public UserResource(final UserDaoService userDaoService) {
        this.userDaoService = userDaoService;
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return userDaoService.findAll();

    }

    @GetMapping("/users/{userId}")
    public User retrieveUser(@PathVariable("userId") final int userId) {
        User user = userDaoService.findOne(userId);

        if (user == null)
            throw new UserNotFoundException("id:" + userId);

        return user;
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {

        User savedUser = userDaoService.save(user);

        final URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedUser.getId())
            .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{userId}")
    public void deleteUser(@PathVariable("userId") final int userId) {
        userDaoService.deleteById(userId);

    }

}
