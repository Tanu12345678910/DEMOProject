package com.curdexample.crudexample.dao;
import com.curdexample.crudexample.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CategoryDao extends JpaRepository<Category,Integer> {
    @Modifying
    @Query(value = "update category_product set is_deleted = true where category_id =?1",nativeQuery = true)
    void deleteCategoryById(int categoryId);
}
