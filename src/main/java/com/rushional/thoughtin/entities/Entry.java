package com.rushional.thoughtin.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "entry")
@Getter @Setter
public class Entry {
    public Entry() {
//        Used by Hibernate. Also IDEA doesn't let me make this package-private, which I expected to work
    }

    public Entry(long id, User user, Timestamp creationTimestamp) {
        this.id = id;
        this.user = user;
        this.creationTimestamp = creationTimestamp;
    }

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "creation_timestamp")
    private Timestamp creationTimestamp;
}
