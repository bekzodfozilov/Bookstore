package com.example.bookstore.Dto;

import com.example.bookstore.Dao.Publisher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    private Integer id;

    private String nameuz;

    private Integer cost;

    private String published_date;

    private Integer page_count;

    private AuthorDto authorDto;

    private String genre;

    private PublisherDto publisherDto;

    private boolean isaktive;

    public BookDto(Integer id, String nameuz, Integer cost, String published_date, Integer page_count, String genre, PublisherDto publisherDto, boolean isaktive) {
        this.id = id;
        this.nameuz = nameuz;
        this.cost = cost;
        this.published_date = published_date;
        this.page_count = page_count;
        this.genre = genre;
        this.publisherDto = publisherDto;
        this.isaktive = isaktive;
    }

    public BookDto(Integer id, String nameuz, Integer cost, String published_date, Integer page_count, AuthorDto authorDto, String genre, boolean isaktive) {
        this.id = id;
        this.nameuz = nameuz;
        this.cost = cost;
        this.published_date = published_date;
        this.page_count = page_count;
        this.authorDto = authorDto;
        this.genre = genre;
        this.isaktive = isaktive;
    }
}
