package com.curdexample.crudexample.Controller;

import com.curdexample.crudexample.Services.CategorySeviceInter;
import com.curdexample.crudexample.entities.Category;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("/category")
@Api(value = "Category Service",tags = {"Category Controller"})


public class MyCategoryController {
    private static final Logger logger = LoggerFactory.getLogger(MyCategoryController.class);
    @Autowired
    private CategorySeviceInter categorySeviceInter;

    @GetMapping("/category")
    @ApiOperation(value = "Search Category api")
    public ResponseEntity<?> getCategory(@RequestParam(required = false) String categoryId) {
        logger.info("Calling and starting getAllCategoryAndByID()");
        if (categoryId != null) {
            return new ResponseEntity<>(this.categorySeviceInter.getCategory(Integer.parseInt(categoryId)), HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(this.categorySeviceInter.getCategory(), HttpStatus.OK);
        }
    }

    @PostMapping("/category")
    @ApiOperation(value = "Store Category api")
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        try {
            return new ResponseEntity<>(this.categorySeviceInter.addCategory(category), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Unable to add");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("category/{id}")
    @ApiOperation(value = "Delete Product api")
    public ResponseEntity<String> deleteCategory(@PathVariable(value = "id") int categoryId) {
        try {
            String s = this.categorySeviceInter.deleteCategory(categoryId);
            return new ResponseEntity<>(s, HttpStatus.FOUND);
        } catch (Exception e) {
            logger.error("Unable to delete");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/category/{id}")
    @ApiOperation(value = "Update Category api")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category, @PathVariable(value = "id") int categoryId) {
        try {
            return new ResponseEntity<>(this.categorySeviceInter.updateCategory(category, categoryId), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Unable to update");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
