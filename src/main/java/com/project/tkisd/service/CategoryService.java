package com.project.tkisd.service;

import com.project.tkisd.domain.Category;
import com.project.tkisd.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public Category findById(Long id) {
        Category category = categoryRepository.findFirstById(id);
        return category;
    }
}
