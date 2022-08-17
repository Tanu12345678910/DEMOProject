package com.curdexample.crudexample.Services;

import com.curdexample.crudexample.dto.Productdto;
import com.curdexample.crudexample.entities.Product;

import javax.validation.Valid;
import java.util.List;

public interface ProductServiceInter {
    public List<Product> getProduct();

    public Product getProduct(int productId);
    public Productdto addProduct(Productdto productdto);
    public Product updateProduct(Product product, int productId);

    public String deleteProduct(int productId);

    //public Product patchUpdate(Product product,int productId);
    Product patchProduct(Product product, int productId);

     public Productdto CheckName( Productdto product);
}
