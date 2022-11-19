package com.curdexample.crudexample.Services;

import com.curdexample.crudexample.Exception.ResourceNotFoundException;
import com.curdexample.crudexample.ExternalMethod.ConvertEntityAndDto;
import com.curdexample.crudexample.dao.ProductDao;
import com.curdexample.crudexample.dto.Productdto;
import com.curdexample.crudexample.entities.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements ProductServiceInter {
    @Autowired
    private ProductDao productDao;

    private ConvertEntityAndDto convertEntityAndDto = new ConvertEntityAndDto();
    private Product product2 = new Product();
    private Productdto productdto1 = new Productdto();

    /**
     * This is the service method for fetching all product data
     */
    @Override
    public List<Product> getProduct() {
        List<Product> list = productDao.findAll();
        List<Product> list1 = new ArrayList<Product>();
        for (Product pro : list) {
            if (pro.isDeleted() == false) {
                list1.add(getProduct(pro.getProductId()));
            }
        }
        return list1;
    }

    /**
     * This is the service method for fetching  product data by id
     *
     * @param productId
     * @throws ResourceNotFoundException
     */
    @Override
    public Product getProduct(int productId) throws ResourceNotFoundException {
        Product p = productDao.findById(productId).get();
        Product pro=null;
        if (productDao.findById(productId).isPresent()) {
            if (p.isDeleted() == false) {
                 pro= productDao.findById(productId).get();
            }
        }
        else {
            throw new ResourceNotFoundException();
        }
        return pro;
    }
    /**
     * This is the service method for adding product data
     *
     * @param product
     */
    @Override
    public Productdto addProduct(Productdto product) {
        product2=convertEntityAndDto.dtoToProduct(product);
        return convertEntityAndDto.productToDto( productDao.save(product2));
    }


    /**
     * This is the service method for updating product data
     *
     * @param product
     * @param productId
     * @throws ResourceNotFoundException
     */
    @Override
    public Product updateProduct(Productdto product, int productId) throws ResourceNotFoundException {
          Product update=convertEntityAndDto.dtoToProduct(product);
        if (productDao.findById(productId).isPresent()) {
            update = productDao.findById(productId).get();
            update.setProductName(product.getProductName());
            update.setProductDescription(product.getProductDescription());
            update.setPrice(product.getPrice());
            LocalDate date = LocalDate.now();
            update.setUpdateDate(date);
            update.setActive(update.isActive());
            update.setDeleted(update.isDeleted());
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
                    if (s.charAt(i) != ' ') {
                        throw new RuntimeException("ProductName should contain Letters only");
                    }
                }
            }
            return product;
        }
    }
    public boolean checkForDelete(String productId)
    {
        boolean b;
      Product product=productDao.findById(Integer.valueOf(productId)).get();
      //b=product.isDeleted();
      return product.isDeleted();
    }
}
