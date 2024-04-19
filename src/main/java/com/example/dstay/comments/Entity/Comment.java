package com.example.dstay.comments.Entity;

import com.example.dstay.main.Entity.User;
import jakarta.persistence.*;
import lombok.Data;
import com.example.dstay.news.Entity.News;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "comment")
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "news", nullable = false)
    private News news;

    @ManyToOne
    @JoinColumn(name = "author", nullable = false)
    private User author;

    @Column(name = "content", nullable = true, unique = false, length = 1000)
    private String content;

    @Column(name = "created_at", nullable = false, unique = false)
    private Date creationDate;

    @Column(name = "votes", nullable = true, unique = false)
    private int votes;
}
