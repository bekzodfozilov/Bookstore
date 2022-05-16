package com.example.bookstore.Dao.CustemEntitiy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustemAuthor {

    @Id
    private Integer id;

    @Column(name = "birthdate")
    private String birthdate;

    @Column(name = "name")
    private String name;



}
