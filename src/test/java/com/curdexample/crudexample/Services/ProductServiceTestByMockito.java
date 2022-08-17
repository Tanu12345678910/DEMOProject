package com.curdexample.crudexample.Services;
import com.curdexample.crudexample.dao.ProductDao;
import com.curdexample.crudexample.dto.Productdto;
import com.curdexample.crudexample.entities.Product;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class ProductServiceTestByMockito {
    @Mock
    ProductDao productDao;
    @InjectMocks
    ProductService productService;
    @Mock
    Productdto productdto;
    @Mock
    ModelMapper modelMapper;

    @Test
    void getProduct() {
        List<Product> expectedResult = new ArrayList<>();
        Product product = new Product(2, "OreoShake", "I love oreo shake", 190);
        Product product1 = new Product(3, "DarkChocoShake", "I love Dark Choco shake", 190);
        expectedResult.add(product);
        expectedResult.add(product1);
        Mockito.when(productDao.findAll()).thenReturn(expectedResult);
        List<Product> actualResult = productService.getProduct();
        assertEquals(expectedResult, actualResult);
    }
//@Test
//void addProduct() {
//    Product expectedResult = new Product(1, "Banana Shake", "I do not like Banana shake", 120);
//    Mockito.when(productDao.save(any())).thenReturn(expectedResult);
//    Product actualResult = productService.addProduct(expectedResult);
//    assertEquals(expectedResult, actualResult);
//}
//@Test
//void addProduct() {
//    Productdto expectedResult = new Productdto("Banana Shake", "I do not like Banana shake", 120);
//    Product Result = new Product(1,"Banana Shake", "I do not like Banana shake", 120);
//    Mockito.when(productService.dtoToProduct(any())).thenReturn(Result);
//    Mockito.when(productService.productToDto(any())).thenReturn(expectedResult);
//    Mockito.when(productDao.save(any())).thenReturn(expectedResult);
//    Productdto actualResult = productService.addProduct(expectedResult);
//    assertEquals(expectedResult.getProductName(), actualResult.getProductName());
//}
    @Test
    void updateProduct() {
        Product expectedResult = new Product(2, "Orange Juice", "Sometime I drink it", 150);
        Mockito.when(productDao.findById(expectedResult.getProductId())).thenReturn(Optional.of(expectedResult));
        Mockito.when(productDao.save(any())).thenReturn(expectedResult);
        Product actualResult = productService.updateProduct(expectedResult, expectedResult.getProductId());
        assertEquals(actualResult.getProductId(), expectedResult.getProductId());
    }

    @Test
    void deleteProduct() {
        Product expectedResult = new Product(2, "Lassi", "I do not like lassi", 70);
        Mockito.when(productDao.findById(expectedResult.getProductId())).thenReturn(Optional.of(expectedResult));
        productService.deleteProduct(expectedResult.getProductId());
        Mockito.verify(productDao, Mockito.times(1)).deleteById(expectedResult.getProductId());
    }
}