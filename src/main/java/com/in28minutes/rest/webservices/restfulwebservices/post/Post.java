package com.in28minutes.rest.webservices.restfulwebservices.post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.in28minutes.rest.webservices.restfulwebservices.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
public class Post {
    @Id
    @GeneratedValue
    private Integer id;
    @Size(min = 10)
    private  String description;

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore

    private User user;



    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }
    @Override
    public String toString() {
        return "Post{" +
            "id=" + id +
            ", description='" + description + '\'' +
            '}';
    }

}
