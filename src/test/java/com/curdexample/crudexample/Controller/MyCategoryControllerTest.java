package com.curdexample.crudexample.Controller;

import com.curdexample.crudexample.Services.CategoryService;
import com.curdexample.crudexample.dto.Categorydto;
import com.curdexample.crudexample.entities.Category;
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
@SpringBootTest
class MyCategoryControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Mock
    CategoryService categoryService;
    @InjectMocks
    MyCategoryController myCategoryController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(myCategoryController).build();
    }

    @Test
    void getCategory() throws Exception {
        List<Category> list = new ArrayList<Category>();
        list.add(new Category("PenDrive", "Storage device"));
        list.add(new Category("Laptop", "Digital and Electronic Device"));
        Mockito.when(categoryService.getCategory()).thenReturn(list);
        this.mockMvc.perform(get("/category"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void getOneCategory() throws Exception {
        Category category = new Category("Mouse", "Pointing device");
        Mockito.when(categoryService.getCategory(1)).thenReturn(category);
        this.mockMvc.perform(get("/category").param("categoryId", "1"))
                .andExpect(status().isFound()).andDo(print());
    }

    @Test
    void addCategory() throws Exception {
        Categorydto categorydto = new Categorydto("Mouse", "pointing Device");
        Category category = new Category("Mouse", "pointing Device");
        Mockito.when(categoryService.addCategory(any())).thenReturn(category);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        String jsonBody = mapper.writeValueAsString(categorydto);
        this.mockMvc.perform(post("/category").content(jsonBody).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath(".categoryName").value("Mouse"))
                .andExpect(MockMvcResultMatchers.jsonPath(".categoryDescription").value("pointing Device"))
                .andDo(print());
    }

    @Test
    void deleteCategory() throws Exception {
        Category category = new Category("Mouse", "pointing Device");
        int id = 5;
        Mockito.when(categoryService.deleteCategory(id)).thenReturn("Deleted");
        this.mockMvc.perform(delete("/category/{id}", id))
                .andExpect(status().isFound()).andDo(print());
    }

    @Test
    void updateCategory() throws Exception {
        Category category = new Category("Mouse", "Pointing Device");
        int Id = 4;
        Mockito.when(categoryService.updateCategory(any(), anyInt())).thenReturn(category);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        String jsonBody = mapper.writeValueAsString(category);
        this.mockMvc.perform
                        (put("/category/{id}", Id).content(jsonBody)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath(".categoryName").value("Mouse"))
                .andExpect(MockMvcResultMatchers.jsonPath(".categoryDescription").value("Pointing Device"))
                .andDo(print());
    }
}