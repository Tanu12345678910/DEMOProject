package com.curdexample.crudexample.Controller;

import com.curdexample.crudexample.Services.ProductServiceInter;
import com.curdexample.crudexample.dto.Productdto;
import com.curdexample.crudexample.entities.Product;
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
import org.springframework.web.bind.annotation.PatchMapping;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/product")
@Api(value = "Product Service", tags = {"Product Controller"})

public class MyController {
    private static final Logger logger = LoggerFactory.getLogger(MyController.class);
    @Autowired
    private ProductServiceInter productServiceInter;

    /**
     * This is the controller for fetching Product with id or without Id
     *
     * @param productId
     */

    @GetMapping
    @ApiOperation(value = "Search Product api")
    public ResponseEntity getproduct(@RequestParam(required = false) String productId) {
        try {
            if (productId != null) {
                if (productServiceInter.checkForDelete(productId) == false) {
                    logger.info("Fetching the Product data by Id");
                    Product product=this.productServiceInter.getProduct(Integer.parseInt(productId));
                    return new ResponseEntity(product,HttpStatus.OK);
                } else {
                    logger.info("Given id is not exist");
                    return new ResponseEntity("This Id product is Deleted", HttpStatus.OK);

                }
            } else {
                logger.info("Fetching the all product data");
                List<Product>product1= this.productServiceInter.getProduct();
                return new ResponseEntity(product1, HttpStatus.OK);
            }
        } catch (Exception e) {
            logger.error("Id not found {}", e.getMessage());
            return new ResponseEntity("Id not found", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * This is Used for adding
     *
     * @param product
     */
    @PostMapping
    @ApiOperation(value = "Store Product api")
    public ResponseEntity addProduct(@Valid @RequestBody Productdto product) {
        try {
            Productdto product2 = this.productServiceInter.CheckName(product);
            Productdto product1 = this.productServiceInter.addProduct(product);
            logger.info("Product Added successfully");
            return new ResponseEntity(product1, HttpStatus.CREATED);
        } catch (IllegalArgumentException exc) {
            logger.error("Product name field is null");
            return new ResponseEntity("Product name field is null", HttpStatus.BAD_REQUEST);
        } catch (RuntimeException ex) {
            logger.error("Unable to add as Name of product containing something other than letter");
            return new ResponseEntity("Unable to add as Name of product containing something other than letter", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error("unable to add");
            return new ResponseEntity("Product is not added", HttpStatus.BAD_REQUEST);
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
    public ResponseEntity updateProduct(@RequestBody Productdto product, @PathVariable(value = "id") int productId) {
        try {
            Product product1 = this.productServiceInter.updateProduct(product, productId);
            logger.info("Product added successfully");
            return new ResponseEntity(product1, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("unable to update");
            return new ResponseEntity("Unable to Update", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * This is used to delete the Product of particular id
     *
     * @param productId
     */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete Product api")
    public ResponseEntity deleteProduct(@PathVariable(value = "id") int productId) {
        try {
            if (productId == 0) {
                logger.error("Id does not exist can't perform deletion until id is not found");
                return new ResponseEntity("Id does not exist", HttpStatus.BAD_REQUEST);
            }
            String s = this.productServiceInter.deleteProduct(productId);
            return new ResponseEntity("Deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            logger.error("unable to delete {}", e.getMessage());
            return new ResponseEntity("Delete is not possible", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * This is Patch Controller used for updating
     *
     * @param product
     * @param productId
     */
    @PatchMapping("/{id}")
    public ResponseEntity patchProduct(@RequestBody Product product, @PathVariable(value = "id") int productId) {
        try {
            if (productId == 0) {
                logger.error("Can not perform patch operation on product");
                return new ResponseEntity("Can not perform Patch Operation", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            Product product1 = productServiceInter.patchProduct(product, productId);
            return new ResponseEntity(product1, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("unable to patch data");
            return new ResponseEntity("Not Updated", HttpStatus.BAD_REQUEST);
        }
    }
}
