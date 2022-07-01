package br.com.moneyapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.moneyapi.model.Category;
import br.com.moneyapi.repository.CategoryRepository;

@Service
public class CategoryService {
    
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public Category getOne(Long id) {
        return findById(id);
    }

    private Category findById(Long id) {
        return categoryRepository.findById(id)
            .orElseThrow(() -> new EmptyResultDataAccessException(1));
    }
}
