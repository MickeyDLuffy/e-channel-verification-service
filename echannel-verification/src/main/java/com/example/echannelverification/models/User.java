package com.example.echannelverification.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;

    private String name;

    private String password;

    @OneToMany(mappedBy = "user")
    private Set<EventSource> eventSourceSet;

}
