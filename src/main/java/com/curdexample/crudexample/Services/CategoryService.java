package com.curdexample.crudexample.Services;
import com.curdexample.crudexample.Exception.ResourceNotFoundException;
import com.curdexample.crudexample.dao.CategoryDao;
import com.curdexample.crudexample.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CategoryService implements CategorySeviceInter{
    @Autowired
    private CategoryDao categoryDao;
    @Override
    public List<Category> getCategory() {
        return categoryDao.findAll();
    }
    @Override
    public Category getCategory(int categoryId) {
      return categoryDao.findById(categoryId).get();
    }
    @Override
    public Category addCategory(Category category) {
        categoryDao.save(category);

        return category;
    }
    @Override
    public void deleteCategory(int categoryId) {
        categoryDao.deleteCategoryById(categoryId);

    }
    @Override
    public Category updateCategory(Category category, int categoryId) {
        Category update;
        if(categoryDao.findById(categoryId).isPresent()) {
            update = categoryDao.findById(categoryId).get();
            update.setCategoryName(category.getCategoryName());
            update.setCategoryDescription(category.getCategoryDescription());
            // update.setCreateDate(product.getCreateDate());updateDate=new Date(System.currentTimeMillis());
            Date date= new Date(System.currentTimeMillis());
            update.setUpdateDate(String.valueOf(date));
            update.setActive(category.isActive());
            update.setDeleted(category.isDeleted());
            categoryDao.save(update);
        }
        else {
            throw new ResourceNotFoundException();
        }
        return update;
    }
}
