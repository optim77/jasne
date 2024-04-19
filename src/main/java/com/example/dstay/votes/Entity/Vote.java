package com.example.dstay.votes.Entity;

import com.example.dstay.comments.Entity.Comment;
import com.example.dstay.main.Entity.User;
import com.example.dstay.news.Entity.News;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "vote")
@Data
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user", nullable = false, referencedColumnName = "id")
    private User user;

    @OneToOne
    @JoinColumn(name = "news", referencedColumnName = "id")
    private News news;

    @OneToOne
    @JoinColumn(name = "comment", referencedColumnName = "id")
    private Comment comment;
}
