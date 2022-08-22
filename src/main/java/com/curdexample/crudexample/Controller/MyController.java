package com.curdexample.crudexample.Controller;
import com.curdexample.crudexample.BaseResponse;
import com.curdexample.crudexample.Services.ProductServiceInter;
import com.curdexample.crudexample.dto.Productdto;
import com.curdexample.crudexample.entities.Product;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/product")
@Api(value = "Product Service", tags = {"Product Controller"})
public class MyController {
    private static final Logger logger = LoggerFactory.getLogger(MyController.class);
    @Autowired
    private ProductServiceInter productServiceInter;

    /**
     * This is the controller for fetching Product with id or without Id
     * @param productId
     */

    @GetMapping
    @ApiOperation(value = "Search Product api")
    public BaseResponse getproduct(@RequestParam(required = false) String productId) {
        try {
            if (productId != null) {
                     if(productServiceInter.checkForDelete(productId)==false) {
                    logger.info("Fetching the Product data by Id");
                    return new BaseResponse("Data Fetched by Id", HttpStatus.OK, this.productServiceInter.getProduct(Integer.parseInt(productId)));
                     }
                     else
                     {
                         logger.info("Given id is not exist");
                         return new BaseResponse("This Id product is Deleted", HttpStatus.OK);

                     }
                } else {
                logger.info("Fetching the all product data");
                return new BaseResponse("All Data Fetched", HttpStatus.OK, this.productServiceInter.getProduct());
            }
        } catch (Exception e) {
            logger.error("Id not found {}", e.getMessage());
            return new BaseResponse("Id not found", HttpStatus.BAD_REQUEST);
        }
    }
    /**
     * This is Used for adding
     * @param product
     */
    @PostMapping
    @ApiOperation(value = "Store Product api")
    public BaseResponse addProduct( @Valid @RequestBody Productdto product) {
        try {
            Productdto product2=this.productServiceInter.CheckName(product);
            Productdto product1 = this.productServiceInter.addProduct(product);
            logger.info("Product Added successfully");
            return new BaseResponse("Product added successfully", HttpStatus.CREATED, product1);
        }catch (IllegalArgumentException exc)
        {
            logger.error("Product name field is null");
            return new BaseResponse("Product name field is null",HttpStatus.BAD_REQUEST);
        }
        catch (RuntimeException ex)
        {
            logger.error("Unable to add as Name of product containing something other than letter");
            return new BaseResponse("Unable to add as Name of product containing something other than letter",HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            logger.error("unable to add");
            return new BaseResponse("Product is not added", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * This is the controller used to update the product of particular id
     *
     * @param product
     * @param productId
     */
    @PutMapping("/{id}")
    @ApiOperation(value = "Update Product api")
    public BaseResponse updateProduct(@RequestBody Productdto product, @PathVariable(value = "id") int productId) {
        try {
            Product product1 = this.productServiceInter.updateProduct(product, productId);
            logger.info("Product added successfully");
            return new BaseResponse("Updated successfully", HttpStatus.CREATED, product1);
        } catch (Exception e) {
            logger.error("unable to update");
            return new BaseResponse("Unable to Update", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * This is used to delete the Product of particular id
     * @param productId
     */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete Product api")
    public BaseResponse deleteProduct(@PathVariable(value = "id") int productId) {
        try {
            if (productId == 0) {
                logger.error("Id does not exist can't perform deletion until id is not found");
                return new BaseResponse("Id does not exist", HttpStatus.BAD_REQUEST);
            }
            String s = this.productServiceInter.deleteProduct(productId);
            return new BaseResponse("Deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            logger.error("unable to delete {}", e.getMessage());
            return new BaseResponse("Delete is not possible", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * This is Patch Controller used for updating
     * @param product
     * @param productId
     */
    @PatchMapping("/{id}")
    public BaseResponse patchProduct(@RequestBody Product product, @PathVariable(value = "id") int productId) {
        try {
            if (productId == 0) {
                logger.error("Can not perform patch operation on product");
                return new BaseResponse("Can not perform Patch Operation", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            Product product1 = productServiceInter.patchProduct(product, productId);
            return new BaseResponse("Updated successfully", HttpStatus.OK, product1);
        } catch (Exception e) {
            logger.error("unable to patch data");
            return new BaseResponse("Not Updated", HttpStatus.BAD_REQUEST);
        }
    }
}
