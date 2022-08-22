package com.curdexample.crudexample.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

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
    @Size(max = 20)
    private String productName;
    @Column
    @NotNull
    @Size(max = 30)
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
  public Product(int productId,String productName,String productDescription,int price )
  {
      this.productId=productId;
      this.productName=productName;
      this.productDescription=productDescription;
      this.price=price;
  }
}
