package com.curdexample.crudexample.Controller;
import com.curdexample.crudexample.Services.CategorySeviceInter;
import com.curdexample.crudexample.entities.Category;
import com.curdexample.crudexample.entities.Product;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/category")
@Api(value = "Category Service", description = "My Category Service")
public class MyCategoryController {
    private static final Logger logger= LoggerFactory.getLogger(MyCategoryController.class);
    @Autowired
    private CategorySeviceInter categorySeviceInter;
    @GetMapping("/startingCategory")
    @ApiOperation(value = "Starting api")
    public String StartingFunc()
    {
        logger.info("Fatal Error");
        return "This is Starting Category Mapping";
    }
    @GetMapping("/category")
    @ApiOperation(value = "Search Category api")
    public ResponseEntity<?> getCategory(@RequestParam(required = false)String categoryId)
    {
        if(categoryId!=null)
        {
            return new ResponseEntity<>(this.categorySeviceInter.getCategory(Integer.parseInt(categoryId)), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(this.categorySeviceInter.getCategory(),HttpStatus.OK);
        }
    }
    @PostMapping("/category")
    @ApiOperation(value = "Store Category api")
    public Category addCategory(@RequestBody Category category)
    {
        return this.categorySeviceInter.addCategory(category);
    }
    @DeleteMapping("category/{id}")
    @ApiOperation(value = "Delete Product api")
    public String deleteCategory(@PathVariable(value = "id") int categoryId)
    {
        this.categorySeviceInter.deleteCategory(categoryId);
        return "deleted";
    }
    @PutMapping("/category/{id}")
    @ApiOperation(value = "Update Category api")
    public Category updateCategory(@RequestBody Category category, @PathVariable(value = "id") int categoryId)
    {
        return this.categorySeviceInter.updateCategory(category,categoryId);

    }
}
