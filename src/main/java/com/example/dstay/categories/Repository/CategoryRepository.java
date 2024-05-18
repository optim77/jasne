package com.example.dstay.categories.Repository;

import com.example.dstay.categories.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
