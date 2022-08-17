package com.curdexample.crudexample.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;
    @Column
    @Size(max=20)
    private String productName;
    @Column
    @NotNull
    @Size(max=30)
    private String productDescription;
    @Column
    private int price;
    @Column
    LocalDate createDate = LocalDate.now();
    @Column
    LocalDate updateDate = LocalDate.now();
    @Column
    private boolean isActive = true;
    @Column
    private boolean isDeleted = false;

    public Product() {
    }

    public Product(int productId, String productName, String productDescription, int price) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.price = price;
        this.isDeleted = false;
    }
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
        //this.createDate = createDate==null?null:(Date)createDate.clone();
    }

    public LocalDate getCreateDate() {
        return createDate;
        // return (Date) this.createDate.clone();
    }

    public LocalDate getUpdateDate() {
        return updateDate;
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
