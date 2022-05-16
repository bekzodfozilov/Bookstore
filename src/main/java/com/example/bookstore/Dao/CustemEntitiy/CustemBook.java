package com.example.bookstore.Dao.CustemEntitiy;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
@Data
@Entity

public class CustemBook {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "nameuz")
    private String nameuz;

    @Column(name = "Author")
    private String author;

    @Column(name = "Publisher")
    private String publisher;

    @Column(name = "cost")
    private Integer cost;
}
