package com.curdexample.crudexample.Services;

import com.curdexample.crudexample.dto.Categorydto;
import com.curdexample.crudexample.entities.Category;

import java.util.List;

public interface CategorySeviceInter {
    public List<Category> getCategory();

    public Category getCategory(int categoryId);

    public Category addCategory(Categorydto category);

    public String deleteCategory(int categoryId);

    public Category updateCategory(Categorydto category, int categoryId);

    Categorydto CheckNameCategory(Categorydto category);
}
