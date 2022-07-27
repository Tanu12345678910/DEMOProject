package com.curdexample.crudexample.Services;
import com.curdexample.crudexample.dao.ProductDao;
import com.curdexample.crudexample.entities.Product;
import com.curdexample.crudexample.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductService implements ProductServiceInter {
    @Autowired
    private ProductDao productDao;
    @Override
    public List<Product> getProduct() {
        return productDao.findAll();
    }
    @Override
    public Product getProduct(int productId) throws ResourceNotFoundException
    {
        Product product;
        if(productDao.findById(productId).isPresent()){
             product = productDao.findById(productId).get();
        }
        else{
              throw new ResourceNotFoundException();
        }
        return product;
    }

    @Override
    public Product addProduct(Product product) {
            productDao.save(product);
            return product;
    }
        @Override
        public Product updateProduct (Product product,int productId) throws ResourceNotFoundException{
        Product update;
        if(productDao.findById(productId).isPresent()) {
            update = productDao.findById(productId).get();
            update.setProductName(product.getProductName());
            update.setProductDescription(product.getProductDescription());
            update.setPrice(product.getPrice());
            // update.setCreateDate(product.getCreateDate());updateDate=new Date(System.currentTimeMillis());
            Date date = new Date(System.currentTimeMillis());
            update.setUpdateDate(date);
            update.setActive(product.isActive());
            update.setDeleted(product.isDeleted());
            productDao.save(update);
        }
        else {
            throw new ResourceNotFoundException();
             }
            return update;
        }
    @Override
    public String deleteProduct(int productId)throws ResourceNotFoundException {
        if (productDao.findById(productId).isPresent()) {
            productDao.deleteById(productId);
            return "deleted";
        }
        else {
            throw new ResourceNotFoundException();
        }
    }
    @Override
    public Product patchProduct(Product product, int productId) throws ResourceNotFoundException {
        Product patchupdate;
        if(productDao.findById(productId).isPresent()) {
             patchupdate = productDao.findById(productId).get();
            patchupdate.setProductName(product.getProductName());
            patchupdate.setProductDescription(product.getProductDescription());
            patchupdate.setPrice(product.getPrice());
            Date date = new Date(System.currentTimeMillis());
            patchupdate.setUpdateDate(date);
            patchupdate.setActive(product.isActive());
            patchupdate.setDeleted(product.isDeleted());
            productDao.save(patchupdate);
        }
        else
        {
            throw new ResourceNotFoundException();
        }
        return patchupdate;
    }
}
