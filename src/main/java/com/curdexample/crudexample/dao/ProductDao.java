package com.curdexample.crudexample.dao;

import com.curdexample.crudexample.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ProductDao extends JpaRepository<Product, Integer> {
    @Modifying
    @Query(value = "update product set is_deleted = true where product_id =?1", nativeQuery = true)
    void deleteById(int categoryId);
}
