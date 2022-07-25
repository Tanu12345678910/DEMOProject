package com.curdexample.crudexample.Services;
import java.util.List;

import com.curdexample.crudexample.entities.Product;

public interface ProductServiceInter {
    public List<Product>getProduct();
   public Product getProduct(int productId);
    public Product addProduct(Product product);
    public Product updateProduct(Product product,int productId);
    public void deleteProduct(int productId);
    //public Product patchUpdate(Product product,int productId);
    Product patchProduct(Product product, int productId);
}
