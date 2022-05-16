package com.example.bookstore.Dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "author")
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    @Id
    @GeneratedValue(generator = "author_id_seq")
    @SequenceGenerator(name = "author_id_seq",sequenceName = "author_id_seq",allocationSize = 1)
    private Integer id;

    @Column(name = "birthdate")
    private String birthdate;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "author")
    private List<Book> books;

    @Column(name = "isaktive")
    private boolean isaktive;

    public Author(Integer id, String birthdate, String firstname, String lastname, boolean isaktive) {
        this.id = id;
        this.birthdate = birthdate;
        this.firstname = firstname;
        this.lastname = lastname;
        this.isaktive = isaktive;
    }
}
