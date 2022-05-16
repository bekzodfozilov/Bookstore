package com.example.bookstore.Dao.CustemEntitiy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Access;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class CustemPublisher {

    @Id
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "adres_id")
    private Integer adres_id;
}
