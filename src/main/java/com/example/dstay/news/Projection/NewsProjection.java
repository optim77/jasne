package com.example.dstay.news.Projection;

import com.example.dstay.news.Entity.News;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;

@Projection(name = "excerpt", types = { News.class })
public interface NewsProjection {
    Long getId();
    String getContent();
    Date getDate();
}
