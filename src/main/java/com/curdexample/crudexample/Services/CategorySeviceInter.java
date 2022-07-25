package com.curdexample.crudexample.Services;

import com.curdexample.crudexample.entities.Category;

import java.util.List;

public interface CategorySeviceInter {
    public List<Category>getCategory();
    public Category getCategory(int categoryId);
    public Category addCategory(Category category);
    public void deleteCategory(int categoryId);
    public Category updateCategory(Category category, int categoryId);
}
