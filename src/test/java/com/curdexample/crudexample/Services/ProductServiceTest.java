package com.curdexample.crudexample.Services;
import com.curdexample.crudexample.dao.ProductDao;
import com.curdexample.crudexample.entities.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.internal.matchers.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ProductServiceTest {
    @Autowired
    ProductDao productDao;
    @Test
    void getProduct() {
        List<Product> products = productDao.findAll();
        assertEquals(products.size(),9);
    }
    @Test
    void addProduct() {
     Product p=new Product();
     p.setProductId(7);
     p.setProductName("MoMos");
     p.setProductDescription("I do not like MoMos");
     p.setPrice(80);
     productDao.save(p);
     assertNotNull(productDao.findById(7).get());
    }
    @Test
    void updateProduct() {
        Product p=productDao.findById(7).get();
        p.setPrice(300);
        productDao.save(p);
        assertEquals(300,productDao.findById(7).get().getPrice());
    }
    @Test
    void patchProduct() {
        Product p=productDao.findById(7).get();
        p.setPrice(300);
        productDao.save(p);
        assertEquals(300,productDao.findById(7).get().getPrice());
    }
}