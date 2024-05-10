package com.example.dstay.news.Repository;

import com.example.dstay.news.Entity.News;
import com.example.dstay.news.Projection.NewsProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = NewsProjection.class)
public interface NewsActivityRepository extends PagingAndSortingRepository<News, Long> {

    Page<NewsProjection> findAllByAuthor_IdOrderByVotes(Long authorId, Pageable pageable);
}
