package com.example.dstay.news.Repository;

import com.example.dstay.news.Entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;

import java.lang.reflect.Array;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

    Page<News> findAllByTitle(String phrase, Pageable pageable);

}
