package com.curdexample.crudexample.Controller;

import com.curdexample.crudexample.BaseResponse;
import com.curdexample.crudexample.Services.CategorySeviceInter;
import com.curdexample.crudexample.dto.Categorydto;
import com.curdexample.crudexample.entities.Category;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public BaseResponse getCategory(@RequestParam(required = false) String categoryId) {
        try {
            if (categoryId != null) {
                logger.info("Fetched Category by Id");
                return new BaseResponse("Id Data Fetched", HttpStatus.OK, this.categorySeviceInter.getCategory(Integer.parseInt(categoryId)));
            } else {
                logger.info("Fetched All Category");
                return new BaseResponse("All data Fetched Successfully", HttpStatus.OK, this.categorySeviceInter.getCategory());
            }
        } catch (Exception e) {
            logger.error("id does not exist {}", e.getMessage());
            return new BaseResponse("id does not exist", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * This is controller used for saving category
     *
     * @param category
     */
    @PostMapping
    @ApiOperation(value = "Store Category api")
    public BaseResponse addCategory(@Valid @RequestBody Categorydto category) {
        try {
            logger.info("Category added");
            Categorydto c = this.categorySeviceInter.CheckNameCategory(category);
            return new BaseResponse("Added successfully", HttpStatus.OK, this.categorySeviceInter.addCategory(category));
        } catch (IllegalArgumentException exc) {
            logger.error("Name is Null");
            return new BaseResponse("Category Name is null", HttpStatus.BAD_REQUEST);
        } catch (RuntimeException ex) {
            logger.error("Unable to add as Name of product containing something other than letter");
            return new BaseResponse("Unable to add as Name of product containing something other than letter", HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            logger.error("Unable to add {}", e.getMessage());
            return new BaseResponse("Not able to add", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * This is controller used for Deleting category of particular id
     *
     * @param categoryId
     */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete Product api")
    public BaseResponse deleteCategory(@PathVariable(value = "id") int categoryId) {
        try {
            logger.info("deleted product");
            String s = this.categorySeviceInter.deleteCategory(categoryId);
            return new BaseResponse("Deleted Successfully", HttpStatus.OK, s);
        } catch (Exception e) {
            logger.error("Unable to delete {}", e.getMessage());
            return new BaseResponse("Id does not exist", HttpStatus.BAD_REQUEST);
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
    public BaseResponse updateCategory(@RequestBody Categorydto category, @PathVariable(value = "id") int categoryId) {
        try {
            logger.info("Update category");
            return new BaseResponse("Update Successfully", HttpStatus.OK, this.categorySeviceInter.updateCategory(category, categoryId));
        } catch (Exception e) {
            logger.error("Unable to update");
            return new BaseResponse("Id does not exist", HttpStatus.BAD_REQUEST);
        }
    }
}
