package com.curdexample.crudexample.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;
    @Column
    private String productName;
    @Column
    private String productDescription;
    @Column
    private int price;
    @Column
    //@Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date createDate = new Date(System.currentTimeMillis());
    @Column
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date updateDate = new Date(System.currentTimeMillis());
    @Column
    private boolean isActive = true;

    public Product(int productId, String productName, String productDescription, int price) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.price = price;
        this.isDeleted = false;
    }

    @Column
    private boolean isDeleted = false;

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", price=" + price +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", isActive=" + isActive +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
