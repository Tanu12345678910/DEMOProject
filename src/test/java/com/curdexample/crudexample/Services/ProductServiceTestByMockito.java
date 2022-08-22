package com.curdexample.crudexample.Services;
import com.curdexample.crudexample.ExternalMethod.ConvertEntityAndDto;
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
    ConvertEntityAndDto convertEntityAndDto;

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
    @Test
void addProduct() {
   Productdto expectedResult = new Productdto( "Banana Shake", "I do not like Banana shake", 120);
    Product Result = new Product( 1,"Banana Shake", "I do not like Banana shake", 120);
    Mockito.when(productDao.save(Result)).thenReturn(Result);
    Mockito.when(convertEntityAndDto.dtoToProduct(any())).thenReturn(Result);
    Mockito.when(convertEntityAndDto.productToDto(any())).thenReturn(expectedResult);
    Productdto actualResult = productService.addProduct(expectedResult);
    assertEquals(expectedResult.getProductName(), actualResult.getProductName());
}
    @Test
    void updateProduct() {
        Productdto expectedResult = new Productdto("Orange Juice", "Sometime I drink it", 150);
          Product p=new Product(1,"Orange Juice", "Sometime I drink it", 150);
        Mockito.when(productDao.findById(p.getProductId())).thenReturn(Optional.of(p));
        Mockito.when(productDao.save(p)).thenReturn(p);
        Product actualResult = productService.updateProduct(expectedResult, p.getProductId());
        assertEquals(actualResult.getProductName(), expectedResult.getProductName());
    }

    @Test
    void deleteProduct() {
        Product expectedResult = new Product(2, "Lassi", "I do not like lassi", 70);
        Mockito.when(productDao.findById(expectedResult.getProductId())).thenReturn(Optional.of(expectedResult));
        productService.deleteProduct(expectedResult.getProductId());
        Mockito.verify(productDao, Mockito.times(1)).deleteById(expectedResult.getProductId());
    }
}