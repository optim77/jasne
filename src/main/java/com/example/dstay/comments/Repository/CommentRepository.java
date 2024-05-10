package com.example.dstay.comments.Repository;

import com.example.dstay.comments.Entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findAllByNews(Long news, Pageable pageable);
    Page<Comment> findByNews_Id_OrderByCreationDateDesc(Long id, Pageable pageable);
    Long countByNews_Id(Long news_id);
    Page<Comment> findAllByAuthor_IdOrderByCreationDateDesc(Long id, Pageable pageable);
}
