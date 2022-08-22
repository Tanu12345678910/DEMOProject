package com.curdexample.crudexample.Services;

import com.curdexample.crudexample.Exception.ResourceNotFoundException;
import com.curdexample.crudexample.ExternalMethod.ConvertEntityAndDto;
import com.curdexample.crudexample.dao.CategoryDao;
import com.curdexample.crudexample.dto.Categorydto;
import com.curdexample.crudexample.entities.Category;
import com.curdexample.crudexample.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService implements CategorySeviceInter {
    @Autowired
    private CategoryDao categoryDao;
    private ConvertEntityAndDto convertEntityAndDto=new ConvertEntityAndDto();
    private Category category1=new Category();

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
        Category category = categoryDao.findById(categoryId).get();
        Category pro=null;
        if (categoryDao.findById(categoryId).isPresent()) {
            if (category.isDeleted() == false) {
                pro= categoryDao.findById(categoryId).get();
            }
        }
        else {
            throw new ResourceNotFoundException();
        }
        return pro;
    }

    /**
     * This is the service method for saving category data
     *
     * @param category
     */
    @Override
    public Category addCategory(Categorydto category) {
         try {
              Category category1=convertEntityAndDto.dtoToCategory(category);
             return categoryDao.save(category1);
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
            categoryDao.deleteById(categoryId);
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
    public Category updateCategory(Categorydto category, int categoryId) {
        Category update=convertEntityAndDto.dtoToCategory(category);
        if (categoryDao.findById(categoryId).isPresent()) {
            update = categoryDao.findById(categoryId).get();
            update.setCategoryName(category.getCategoryName());
            update.setCategoryDescription(category.getCategoryDescription());
            LocalDate date = LocalDate.now();
            update.setUpdateDate(date);
            update.setActive(update.isActive());
            update.setDeleted(update.isDeleted());
            categoryDao.save(update);
        } else {
            throw new ResourceNotFoundException();
        }
        return update;
    }

    @Override
    public Categorydto CheckNameCategory(Categorydto category) {
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

    /**
     * Method for getting isDelete value
     * @param categoryID
     * @return
     */
    public boolean checkForDelete(String categoryID)
    {
        Category category=categoryDao.findById(Integer.valueOf(categoryID)).get();
        return category.isDeleted();
    }
}
