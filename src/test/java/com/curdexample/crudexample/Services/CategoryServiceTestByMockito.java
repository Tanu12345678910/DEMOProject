package com.curdexample.crudexample.Services;

import com.curdexample.crudexample.dao.CategoryDao;
import com.curdexample.crudexample.dto.Categorydto;
import com.curdexample.crudexample.entities.Category;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static net.bytebuddy.matcher.ElementMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CategoryServiceTestByMockito {
    @Mock
    CategoryDao categoryDao;
    @InjectMocks
    CategoryService categoryService;

    @Test
    void getCategory() {
        List<Category> expectedCategory = new ArrayList<>();
        Category c1 = new Category();
        c1.setCategoryName("Tablet");
        c1.setCategoryDescription("It comes under digital gagdets");
        Category c2 = new Category();
        c2.setCategoryName("Pendrive");
        c2.setCategoryDescription("It is a storage device");
        Mockito.when(categoryDao.findAll()).thenReturn(expectedCategory);
        List<Category> actualCategory = categoryService.getCategory();
        assertEquals(actualCategory.size(), expectedCategory.size());
    }

    @Test
    void testGetCategory() {
        Category category = new Category();
        category.setCategoryName("Keyboard");
        category.setCategoryDescription("It is a input device");
        Mockito.when(categoryDao.findById(category.getCategoryId())).thenReturn(Optional.of(category));
        Category c = categoryService.getCategory(category.getCategoryId());
        assertEquals(c.getCategoryName(), category.getCategoryName());


    }

    @Test
    void addCategory() {
        Categorydto expectedCategory = new Categorydto();
        expectedCategory.setCategoryName("Laptop");
        expectedCategory.setCategoryDescription("It is a digital electronic device");
        Category c=new Category();
        c.setCategoryName("Laptop");
        c.setCategoryDescription("It is a digital electronic device");
        Mockito.when(categoryDao.save(c)).thenReturn(c);
        Category actualCategory = categoryService.addCategory(expectedCategory);
        assertEquals(actualCategory.getCategoryName(), expectedCategory.getCategoryName());
    }

    @Test
    void deleteCategory() {
        Category expectedCategory = new Category();
        expectedCategory.setCategoryId(3);
        expectedCategory.setCategoryName("Mic");
        expectedCategory.setCategoryDescription("It is a  electronic device used for voice enlarging");
        System.out.println(expectedCategory.getCategoryId());
        //Mockito.when(categoryDao.findById(expectedCategory.getCategoryId())).thenReturn(Optional.of(expectedCategory));
        categoryService.deleteCategory(expectedCategory.getCategoryId());
        Mockito.verify(categoryDao, Mockito.times(1)).deleteById((expectedCategory.getCategoryId()));
    }

    @Test
    void updateCategory() {
        Category expectedCategory = new Category();
        expectedCategory.setCategoryName("Mouse");
        expectedCategory.setCategoryDescription("It is a pointing device");
          Categorydto c=new Categorydto("Mouse","It is a pointing device");
        Mockito.when(categoryDao.findById(expectedCategory.getCategoryId())).thenReturn(Optional.of(expectedCategory));
        Category actualCategory = categoryService.updateCategory(c, expectedCategory.getCategoryId());
        assertEquals(actualCategory.getCategoryName(), expectedCategory.getCategoryName());

    }
}