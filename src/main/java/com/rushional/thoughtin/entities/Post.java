package com.rushional.thoughtin.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "post")
@Getter @Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entry_id")
    private Entry entry;

    @Column(name = "text")
    private String text;

    @Column(name = "creation_time")
    private Time creationTime;

    @Column(name = "last_edit_time")
    private Time lastEditTime;
}
