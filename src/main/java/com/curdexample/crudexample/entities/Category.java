package com.curdexample.crudexample.entities;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.xml.crypto.Data;
import java.util.Date;

@Entity
@Table
public class Category {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int categoryId;
        @Column
        private String categoryName;
        @Column
        private String categoryDescription;
        @Column
        @JsonFormat(pattern ="dd-MM-yyyy")
         private  Date createDate=new Date(System.currentTimeMillis());
        @Column
        @JsonFormat(pattern ="dd-MM-yyyy")
        private Date updateDate=new Date(System.currentTimeMillis());
        @Column
        private  boolean isActive=true;
        @Column
        private boolean isDeleted=false;

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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
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
