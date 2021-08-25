package com.rushional.thoughtin.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "post")
@Getter @Setter
public class Post {
    public Post() {
//        Used by Hibernate
    }

    public Post(long id, Entry entry, String text, Timestamp creationTimestamp, Timestamp lastEditTimestamp) {
        this.id = id;
        this.entry = entry;
        this.text = text;
        this.creationTimestamp = creationTimestamp;
        this.lastEditTimestamp = lastEditTimestamp;
}

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entry_id")
    private Entry entry;

    @Column(name = "text")
    private String text;

    @Column(name = "creation_timestamp")
    private Timestamp creationTimestamp;

    @Column(name = "last_edit_timestamp")
    private Timestamp lastEditTimestamp;
}
