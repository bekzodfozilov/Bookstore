package com.example.bookstore.Dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@Table(name = "address")
@AllArgsConstructor
@NoArgsConstructor
public class Adres {

    @Id
    @GeneratedValue(generator = "address_id_seq")
    @SequenceGenerator(name = "address_id_seq",sequenceName = "address_id_seq",allocationSize = 1)
    private Integer id;

    @Column(name = "district_id")
    private Integer district_id;

    @Column(name = "region_id")
    private Integer region_id;

    @Column(name = "street")
    private String street;

    @OneToOne(fetch = FetchType.EAGER,mappedBy = "adres" )
    private Publisher publishers;

    @Column(name = "isaktive")
    private boolean isaktive;

    public Adres(Integer id, Integer district_id, Integer region_id, String street, boolean isaktive) {
        this.id = id;
        this.district_id = district_id;
        this.region_id = region_id;
        this.street = street;
        this.isaktive = isaktive;
    }
}
