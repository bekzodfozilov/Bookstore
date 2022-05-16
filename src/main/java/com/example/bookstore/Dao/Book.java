package com.example.bookstore.Dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@Table(name = "book")
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(generator = "book_id_seq")
    @SequenceGenerator(name = "book_id_seq",sequenceName = "book_id_seq",allocationSize = 1)
    private Integer id;

    @Column(name = "nameuz")
    private String nameuz;

    @Column(name = "cost")
    private Integer cost;

    @Column(name = "published_date")
    private String publisherDate;

    @Column(name = "page_count")
    private Integer page_count;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id",referencedColumnName = "id")
    private Author author;

    @Column(name = "genre")
    private String genre;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "publisher_id",referencedColumnName = "id")
    private Publisher publisher;

    @Column(name = "isaktive")
    private boolean isaktive;


    public Book(Integer id, String nameuz, Integer cost, String publisherDate, Integer page_count, String genre , boolean isaktive) {
        this.id = id;
        this.nameuz = nameuz;
        this.cost = cost;
        this.publisherDate = publisherDate;
        this.page_count = page_count;
        this.genre = genre;
        this.isaktive = isaktive;
    }


}
