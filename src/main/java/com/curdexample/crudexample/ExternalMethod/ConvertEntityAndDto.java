package com.curdexample.crudexample.ExternalMethod;

import com.curdexample.crudexample.dto.Categorydto;
import com.curdexample.crudexample.dto.Productdto;
import com.curdexample.crudexample.entities.Category;
import com.curdexample.crudexample.entities.Product;

public class ConvertEntityAndDto {
    public Product dtoToProduct(Productdto productdto) {
        Product product2=new Product();
        product2.setProductName(productdto.getProductName());
        product2.setProductDescription(productdto.getProductDescription());
        product2.setPrice(productdto.getPrice());
        return product2;
    }
    public Productdto productToDto(Product product) {
        Productdto productdto1=new Productdto();
        productdto1.setProductName(product.getProductName());
        productdto1.setProductDescription(product.getProductDescription());
        productdto1.setPrice(product.getPrice());
        return productdto1;
    }
    public Category dtoToCategory(Categorydto categorydto) {
        Category category=new Category();
        category.setCategoryName(categorydto.getCategoryName());
        category.setCategoryDescription(categorydto.getCategoryDescription());
        return category;
    }
//    public Categorydto categoryToDto(Category category) {
//        Categorydto categorydto=new Categorydto();
//        categorydto.setCategoryName(category.getCategoryName());
//       categorydto.setCategoryDescription(category.getCategoryDescription());
//       return categorydto;
//    }
}
