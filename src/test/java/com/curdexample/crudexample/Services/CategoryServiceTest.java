package com.curdexample.crudexample.Services;

import com.curdexample.crudexample.dao.CategoryDao;
import com.curdexample.crudexample.entities.Category;
import com.curdexample.crudexample.entities.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryServiceTest {
    @Autowired
    CategoryDao categoryDao;
    @Test
    void getCategory() {
        List<Category> category = categoryDao.findAll();
        assertEquals(category.size(),3);
    }
    @Test
    void addCategory() {
        Category c=new Category();
        c.setCategoryName("Utensils");
        c.setCategoryDescription("We use it for eating");
        categoryDao.save(c);
        assertEquals(categoryDao.findById(4).get().getCategoryName(),"Utensils");
    }
    @Test
    void updateCategory() {
        Category c=categoryDao.findById(3).get();
        c.setCategoryName("Machine");
        categoryDao.save(c);
        assertEquals(categoryDao.findById(3).get().getCategoryName(),"Machine");
    }
}