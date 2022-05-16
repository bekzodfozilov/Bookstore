package com.example.bookstore.Dao.CustemEntitiy;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class CustemAdres {

    @Id
    private Integer id;

    @Column(name = "district_id")
    private Integer district_id;

    @Column(name = "region_id")
    private Integer region_id;

    @Column(name = "isaktive")
    private boolean isaktive;
}
