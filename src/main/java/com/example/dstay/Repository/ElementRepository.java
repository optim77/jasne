package com.example.dstay.Repository;

import com.example.dstay.Entity.Element;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ElementRepository extends JpaRepository<Element, Long> {
    List<Element> findByCategoryId(Long category);
}
