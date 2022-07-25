package com.curdexample.crudexample.Controller;
import com.curdexample.crudexample.Services.ProductServiceInter;
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
@RequestMapping("/product")
@Api(value = "Product Service", description = "My Product Service")
public class MyController {
    private static final Logger logger= LoggerFactory.getLogger(MyController.class);
  @Autowired
    private ProductServiceInter productServiceInter;
    @GetMapping("/starting")
    @ApiOperation(value = "Starting api")
    public String StartingFunc()
    {
        logger.info("Fatal Error");
        return "This is Starting mapping";
    }
    @GetMapping("/product")
    @ApiOperation(value = "Search Product api")
    public ResponseEntity<?> getproduct(@RequestParam(required = false) String productId) {
        if(productId!=null) {
            return new ResponseEntity<>(this.productServiceInter.getProduct(Integer.parseInt(productId)), HttpStatus.OK);
        }
        else
            return new ResponseEntity<>( this.productServiceInter.getProduct(),HttpStatus.OK);
    }
    @PostMapping("/product")
    @ApiOperation(value = "Store Product api")
    public Product addProduct(@RequestBody Product product)
    {
        return this.productServiceInter.addProduct(product);
    }
    @PutMapping("/product/{id}")
    @ApiOperation(value = "Update Product api")
    public Product updateProduct(@RequestBody Product product,@PathVariable(value = "id") int productId)
    {
        return this.productServiceInter.updateProduct(product,productId);

    }
    @DeleteMapping("product/{id}")
    @ApiOperation(value = "Delete Product api")
    public String deleteProduct(@PathVariable(value = "id") int productId)
    {
        this.productServiceInter.deleteProduct(productId);
        return "Deleted";
    }
   @PatchMapping("/product/{id}")
    public Product patchProduct(@RequestBody Product product,@PathVariable(value="id") int productId)
    {
        return this.productServiceInter.patchProduct(product,productId);
    }
   /* For Exception Handling
    @ResponseStatus(value =HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value =Exception.class)
    public String exceptionHandlerGeneric()
    {
        return "Exception Found";
    }*/
}
