package com.example.dstay.news.Repository;

import com.example.dstay.news.Entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

    Page<News> findAllByTitle(String phrase, Pageable pageable);
    Optional<News> findById(Long id);
    Page<News> findAllByAuthor_IdOrderByVotes(Long id, Pageable pageable);

    Page<News> findByCategory_Name(String category, Pageable pageable);

    @Query("SELECT n FROM News n WHERE n.created_at >= :startDate AND n.created_at < :endDate ORDER BY n.votes DESC")
    Page<News> findTop10NewsByVotesInCurrentWeek(@Param("startDate") Date startDate, @Param("endDate") Date endDate, Pageable pageable);

    Page<News> findAll(Pageable pageable);

    void deleteAllByCategoryId(Long id);
}
