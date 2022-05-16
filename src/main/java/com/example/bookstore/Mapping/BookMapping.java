package com.example.bookstore.Mapping;

import com.example.bookstore.Dao.Book;
import com.example.bookstore.Dao.Publisher;
import com.example.bookstore.Dto.AdresDto;
import com.example.bookstore.Dto.AuthorDto;
import com.example.bookstore.Dto.BookDto;
import com.example.bookstore.Dto.PublisherDto;

public class BookMapping {
    public static BookDto ToDto(Book book){
        AuthorDto authorDto = AuthorMapping.ToDto(book.getAuthor());
        PublisherDto publisherDto = PublisherMapping.ToDto(book.getPublisher());
        return new BookDto(
                book.getId(),
                book.getNameuz(),
                book.getCost(),
                book.getPublisherDate(),
                book.getPage_count(),
                authorDto,
                book.getGenre(),
                publisherDto,
                book.isIsaktive()
                );
    }

    public static BookDto ToDtoS(Book book){
        PublisherDto publisherDto = PublisherMapping.ToDto(book.getPublisher());
        return new BookDto(
                book.getId(),
                book.getNameuz(),
                book.getCost(),
                book.getPublisherDate(),
                book.getPage_count(),
                book.getGenre(),
                publisherDto,
                book.isIsaktive()
        );
    }

    public static BookDto ToDtoP(Book book){
        AuthorDto authorDto = AuthorMapping.ToDto(book.getAuthor());
        return new BookDto(
                book.getId(),
                book.getNameuz(),
                book.getCost(),
                book.getPublisherDate(),
                book.getPage_count(),
                authorDto,
                book.getGenre(),
                book.isIsaktive()
        );
    }

    public static Book ToEntity(BookDto bookDto) {
        return new Book(
                bookDto.getId(),
                bookDto.getNameuz(),
                bookDto.getCost(),
                bookDto.getPublished_date(),
                bookDto.getPage_count(),
                bookDto.getGenre(),
                bookDto.isIsaktive()
        );
    }
}
