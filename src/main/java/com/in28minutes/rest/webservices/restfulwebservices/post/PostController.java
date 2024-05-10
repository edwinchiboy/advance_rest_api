package com.in28minutes.rest.webservices.restfulwebservices.post;

import com.in28minutes.rest.webservices.restfulwebservices.jpa.UserRepository;
import com.in28minutes.rest.webservices.restfulwebservices.user.User;
import com.in28minutes.rest.webservices.restfulwebservices.user.UserNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class PostController {
    private final UserRepository userRepository;

    public PostController(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrievePostForUser(@PathVariable int id){

        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty())
            throw new UserNotFoundException("id:" + id);

       return user.get().getPosts();

    }


}
