package com.curdexample.crudexample.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;
    @Column
    @Size(max=20)
    private String categoryName;
    @Column
    @NotNull
    @Size(max = 30)
    private String categoryDescription;
    @Column
    LocalDate createDate = LocalDate.now();
    @Column
    LocalDate updateDate = LocalDate.now();

    @Column
    private boolean isActive = true;
    @Column
    private boolean isDeleted = false;

    public Category() {
    }

    public Category(String categoryName, String categoryDescription) {
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public LocalDate getCreateDate() {
        //return (Date) this.createDate.clone();
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
        //this.createDate = createDate==null?null:(Date)createDate.clone();
    }

    public LocalDate getUpdateDate() {
        return updateDate;
        //return (Date) this.updateDate.clone();
    }

    public void setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
        // this.updateDate = updateDate==null?null:(Date)updateDate.clone();
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
