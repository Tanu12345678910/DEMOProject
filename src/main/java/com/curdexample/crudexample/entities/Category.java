package com.curdexample.crudexample.entities;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import javax.persistence.*;
import java.util.Date;
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
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
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date createDate = new Date(System.currentTimeMillis());
    @Column
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date updateDate = new Date(System.currentTimeMillis());
    @Column
    private boolean isActive = true;
    @Column
    private boolean isDeleted = false;
    public Category(String categoryName, String categoryDescription) {
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
    }
}
