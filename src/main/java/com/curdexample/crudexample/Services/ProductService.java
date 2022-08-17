package com.curdexample.crudexample.Services;

import com.curdexample.crudexample.Exception.ResourceNotFoundException;
import com.curdexample.crudexample.dao.ProductDao;
import com.curdexample.crudexample.dto.Productdto;
import com.curdexample.crudexample.entities.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProductService implements ProductServiceInter {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ModelMapper modelMapper;

    /**
     * This is the service method for fetching all product data
     */
    @Override
    public List<Product> getProduct() {
        return productDao.findAll();
    }

    /**
     * This is the service method for fetching  product data by id
     *
     * @param productId
     * @throws ResourceNotFoundException
     */
    @Override
    public Product getProduct(int productId) throws ResourceNotFoundException {
        Product product;
        if (productDao.findById(productId).isPresent()) {
            product = productDao.findById(productId).get();
        } else {
            throw new ResourceNotFoundException();
        }
        return product;
    }

    /**
     * This is the service method for adding product data
     *
     * @param product
     */
    @Override
    public Productdto addProduct(Productdto product) {
        Product product1 = this.dtoToProduct(product);
        this.productDao.save(product1);
        return (this.productToDto(product1));
    }


    /**
     * This is the service method for updating product data
     *
     * @param product
     * @param productId
     * @throws ResourceNotFoundException
     */
    @Override
    public Product updateProduct(Product product, int productId) throws ResourceNotFoundException {
        Product update;
        if (productDao.findById(productId).isPresent()) {
            update = productDao.findById(productId).get();
            update.setProductName(product.getProductName());
            update.setProductDescription(product.getProductDescription());
            update.setPrice(product.getPrice());
            LocalDate date = LocalDate.now();
            update.setUpdateDate(date);
            update.setActive(product.isActive());
            update.setDeleted(product.isDeleted());
            productDao.save(update);
        } else {
            throw new ResourceNotFoundException();
        }
        return update;
    }

    /**
     * This is the service method for deleting product data
     *
     * @param productId
     * @throws ResourceNotFoundException
     */
    @Override
    public String deleteProduct(int productId) throws ResourceNotFoundException {
        if (productDao.findById(productId).isPresent()) {
            productDao.deleteById(productId);
            return "deleted";
        } else {
            throw new ResourceNotFoundException();
        }
    }

    /**
     * This is the service method for updating category data
     *
     * @param product
     * @param productId
     * @throws ResourceNotFoundException
     */
    @Override
    public Product patchProduct(Product product, int productId) throws ResourceNotFoundException {
        Product patchupdate;
        if (productDao.findById(productId).isPresent()) {
            patchupdate = productDao.findById(productId).get();
            patchupdate.setProductName(product.getProductName());
            patchupdate.setProductDescription(product.getProductDescription());
            patchupdate.setPrice(product.getPrice());
            LocalDate date = LocalDate.now();
            patchupdate.setUpdateDate(date);
            patchupdate.setActive(product.isActive());
            patchupdate.setDeleted(product.isDeleted());
            productDao.save(patchupdate);
        } else {
            throw new ResourceNotFoundException();
        }
        return patchupdate;
    }

    @Override
    public Productdto CheckName(Productdto product) {
        if (product.getProductName() == null) {
            throw new IllegalArgumentException("Does not contain name");
        } else {
            int len = product.getProductName().length();
            String s = product.getProductName();
            for (int i = 0; i < len; i++) {
                if (Character.isLetterOrDigit(s.charAt(i)) == false) {
                    throw new RuntimeException("ProductName should contain Letters only");
                }
            }
            return product;
        }
    }
    public Product dtoToProduct(Productdto productdto){
        Product product=this.modelMapper.map(productdto,Product.class);
        return product;
    }
    public Productdto productToDto(Product product)
    {
        Productdto productdto=this.modelMapper.map(product,Productdto.class);
        return productdto;
    }
}
