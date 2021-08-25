package com.rushional.thoughtin.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Getter @Setter
public class User {
    public User() {
    }

    public User(long id, String username) {
        this.id = id;
        this.username = username;
    }

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
//    I'm not sure if I need the actual field. Hibernate documentation doesn't have it at all, only a getter.
    private long id;

    @Column(name = "username", nullable=false, unique=true)
    private String username;
}
