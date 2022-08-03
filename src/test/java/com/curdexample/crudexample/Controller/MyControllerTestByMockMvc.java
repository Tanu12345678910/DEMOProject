package com.curdexample.crudexample.Controller;

import com.curdexample.crudexample.Services.ProductService;
import com.curdexample.crudexample.entities.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ContextConfiguration
@AutoConfigureMockMvc
@SpringBootTest(classes = {MyControllerTestByMockMvc.class})
class MyControllerTestByMockMvc {
    @Autowired
    MockMvc mockMvc;
    @Mock
    ProductService productService;
    @InjectMocks
    MyController myController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(myController).build();
    }

    @Test
    void getproduct() throws Exception {
        List<Product> products = new ArrayList<Product>();
        products.add(new Product(2, "Choclate Shake", "I love Chocolates", 180));
        products.add(new Product(3, "Orange Juice", " It is Healthy ", 80));
        Mockito.when(productService.getProduct()).thenReturn(products);
        this.mockMvc.perform(get("/product")).andExpect(status().isOk()).andDo(print());
    }

    @Test
    void getOneProduct() throws Exception {
        Product product = new Product(2, "Mojito", "It is a cocktail", 180);
        int Id = 2;
        Mockito.when(productService.getProduct(Id)).thenReturn(product);
        this.mockMvc.perform(get("/product/").param("productId", "2"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath(".productName").value("Mojito"))
                .andExpect(MockMvcResultMatchers.jsonPath(".productDescription").value("It is a cocktail"))
                .andExpect(MockMvcResultMatchers.jsonPath(".price").value(180))
                .andDo(print());
    }

    @Test
    void addProduct() throws Exception {
        Product products = new Product(4, "Chocolate Shake", "I love Chocolates", 180);
        Mockito.when(productService.addProduct(any())).thenReturn(products);
        //objectMapper is used to serialize Java Object to JSON
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        String jsonBody = mapper.writeValueAsString(products);
        this.mockMvc.perform(
                        post("/product").content(jsonBody)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound())
                .andDo(print());
    }

    @Test
    void updateProduct() throws Exception {
        Product products = new Product(4, "Chocolate Shake", "I love Chocolates", 180);
        int productId = 4;
        Mockito.when(productService.updateProduct(any(), anyInt())).thenReturn(products);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        String jsonBody = mapper.writeValueAsString(products);
        this.mockMvc.perform
                        (put("/product/{id}", productId).content(jsonBody)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound())
                .andExpect(MockMvcResultMatchers.jsonPath(".productName").value("Chocolate Shake"))
                .andExpect(MockMvcResultMatchers.jsonPath(".productDescription").value("I love Chocolates"))
                .andExpect(MockMvcResultMatchers.jsonPath(".price").value(180)).andDo(print());
    }

    @Test
    void deleteProduct() throws Exception {
        Product product = new Product(3, "Chocolate Shake", "I love Chocolates", 180);
        int id = 4;
        Mockito.when(productService.deleteProduct(id)).thenReturn("Deleted");
        this.mockMvc.perform(delete("/product/{id}", id))
                .andExpect(status().isFound()).andDo(print());
    }
}