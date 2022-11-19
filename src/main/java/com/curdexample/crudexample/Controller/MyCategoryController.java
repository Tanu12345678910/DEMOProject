package com.curdexample.crudexample.Controller;

import com.curdexample.crudexample.Services.CategorySeviceInter;
import com.curdexample.crudexample.dto.Categorydto;
import com.curdexample.crudexample.entities.Category;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/category")
@Api(value = "Category Service", tags = {"Category Controller"})

public class MyCategoryController {
    private static final Logger logger = LoggerFactory.getLogger(MyCategoryController.class);
    @Autowired
    private CategorySeviceInter categorySeviceInter;

    /**
     * This is controller used for fetching Category with id or without ID
     *
     * @param categoryId
     */
    @GetMapping
    @ApiOperation(value = "Search Category api")
    public ResponseEntity getCategory(@RequestParam(required = false) String categoryId) {
        try {
            if (categoryId != null) {
                Category category=this.categorySeviceInter.getCategory(Integer.parseInt(categoryId));
                if (categorySeviceInter.checkForDelete(categoryId) == false) {
                    logger.info("Fetched Category by Id");
                    return new ResponseEntity(category,HttpStatus.OK);
                } else {
                    logger.info("Category id is deleted");
                    return new ResponseEntity("Category of given id is Deleted", HttpStatus.BAD_REQUEST);
                }
            } else {
                logger.info("Fetched All Category");
                List<Category> categories=this.categorySeviceInter.getCategory();
                return new ResponseEntity(categories,HttpStatus.OK);
            }
        } catch (Exception e) {
            logger.error("id does not exist {}", e.getMessage());
            return new ResponseEntity("id does not exist", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * This is controller used for saving category
     *
     * @param category
     */
    @PostMapping
    @ApiOperation(value = "Store Category api")
    public ResponseEntity addCategory(@Valid @RequestBody Categorydto category) {
        try {
            logger.info("Category added");
            Categorydto c = this.categorySeviceInter.CheckNameCategory(category);
            Category category1= this.categorySeviceInter.addCategory(category);
            return new ResponseEntity(category1,HttpStatus.OK);
        } catch (IllegalArgumentException exc) {
            logger.error("Name is Null");
            return new ResponseEntity("Category Name is null", HttpStatus.BAD_REQUEST);
        } catch (RuntimeException ex) {
            logger.error("Unable to add as Name of product containing something other than letter");
            return new ResponseEntity("Unable to add as Name of product containing something other than letter", HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            logger.error("Unable to add {}", e.getMessage());
            return new ResponseEntity("Not able to add", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * This is controller used for Deleting category of particular id
     *
     * @param categoryId
     */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete Product api")
    public ResponseEntity deleteCategory(@PathVariable(value = "id") int categoryId) {
        try {
            logger.info("deleted product");
            String s = this.categorySeviceInter.deleteCategory(categoryId);
            return new ResponseEntity(s,HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Unable to delete {}", e.getMessage());
            return new ResponseEntity("Id does not exist", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * This is controller used for updating category
     *
     * @param category
     * @param categoryId
     */
    @PutMapping("/{id}")
    @ApiOperation(value = "Update Category api")
    public ResponseEntity updateCategory(@RequestBody Categorydto category, @PathVariable(value = "id") int categoryId) {
        try {
            logger.info("Update category");
            Category category1=this.categorySeviceInter.updateCategory(category, categoryId);
            return new ResponseEntity(category1, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Unable to update");
            return new ResponseEntity("Id does not exist", HttpStatus.BAD_REQUEST);
        }
    }

}
