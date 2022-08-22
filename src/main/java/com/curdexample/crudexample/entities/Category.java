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
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;
    @Column
    @Size(max = 20)
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

    public Category(String categoryName, String categoryDescription) {
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
    }
}
