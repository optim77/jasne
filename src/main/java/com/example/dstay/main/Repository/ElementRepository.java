package com.example.dstay.main.Repository;

import com.example.dstay.main.Entity.Element;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
@Repository
public interface ElementRepository extends JpaRepository<Element, Long> {
    Page<Element> findByNameContains(String search, Pageable pageable);
}
