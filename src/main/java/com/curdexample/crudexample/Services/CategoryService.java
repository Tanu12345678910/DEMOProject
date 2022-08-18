package com.curdexample.crudexample.Services;

import com.curdexample.crudexample.Exception.ResourceNotFoundException;
import com.curdexample.crudexample.dao.CategoryDao;
import com.curdexample.crudexample.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService implements CategorySeviceInter {
    @Autowired
    private CategoryDao categoryDao;

    /**
     * This is the service method for getting  all data of category
     */
    @Override
    public List<Category> getCategory() {
        List<Category> list = categoryDao.findAll();
        List<Category> list1 = new ArrayList<Category>();
        for (Category c : list) {
            if (c.isDeleted() == false) {
                list1.add(getCategory(c.getCategoryId()));
            }

        }
        return list1;
    }

    /**
     * This is the service method for getting category  data of  given id
     *
     * @param categoryId
     */
    @Override
    public Category getCategory(int categoryId) {
        if (categoryDao.findById(categoryId).isPresent()) {
            return categoryDao.findById(categoryId).get();
        }
        throw new ResourceNotFoundException();
    }

    /**
     * This is the service method for saving category data
     *
     * @param category
     */
    @Override
    public Category addCategory(Category category) {
        try {
            categoryDao.save(category);
            return category;
        } catch (Exception e) {
            throw new ResourceNotFoundException();
        }
    }

    /**
     * This is the service method for deleting category data
     *
     * @param categoryId
     */
    @Override
    public String deleteCategory(int categoryId) {
        if (categoryDao.findById(categoryId).isPresent()) {
            categoryDao.deleteCategoryById(categoryId);
            return "deleted";
        } else {
            throw new ResourceNotFoundException();
        }
    }

    /**
     * This is the service method for updating category data
     *
     * @param category
     * @param categoryId
     */
    @Override
    public Category updateCategory(Category category, int categoryId) {
        Category update;
        if (categoryDao.findById(categoryId).isPresent()) {
            update = categoryDao.findById(categoryId).get();
            update.setCategoryName(category.getCategoryName());
            update.setCategoryDescription(category.getCategoryDescription());
            update.setCreateDate(category.getCreateDate());
            LocalDate date = LocalDate.now();
            update.setUpdateDate(date);
            update.setActive(category.isActive());
            update.setDeleted(category.isDeleted());
            categoryDao.save(update);
        } else {
            throw new ResourceNotFoundException();
        }
        return update;
    }

    @Override
    public Category CheckNameCategory(Category category) {
        if (category.getCategoryName() == null) {
            throw new IllegalArgumentException("Category name is null");
        } else {
            int len = category.getCategoryName().length();
            String s = category.getCategoryName();
            for (int i = 0; i < len; i++) {
                if (Character.isLetterOrDigit(s.charAt(i)) == false && s.charAt(i) != ' ') {
                    throw new RuntimeException("ProductName should contain Letters only");
                }
            }
            return category;
        }
    }
}
