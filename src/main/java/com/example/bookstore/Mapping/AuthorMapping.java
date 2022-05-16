package com.example.bookstore.Mapping;

import com.example.bookstore.Dao.Author;
import com.example.bookstore.Dao.Book;
import com.example.bookstore.Dto.AuthorDto;
import com.example.bookstore.Dto.BookDto;

import java.util.List;
import java.util.stream.Collectors;

public class AuthorMapping {



    public static AuthorDto ToDto(Author author){
        return new AuthorDto(
                author.getId(),
                author.getBirthdate(),
                author.getFirstname(),
                author.getLastname(),
                author.isIsaktive()
        );
    }

    public static AuthorDto ToDtoS(Author author){
        List<BookDto> bookDtos = author.getBooks()
                .stream()
                .filter(Book::isIsaktive)
                .map(BookMapping::ToDtoS)
                .collect(Collectors.toList());
        return new AuthorDto(
                author.getId(),
                author.getBirthdate(),
                author.getFirstname(),
                author.getLastname(),
                bookDtos,
                author.isIsaktive()
        );
    }

    public static Author ToEntity(AuthorDto authorDto) {
        return new Author(
                authorDto.getId(),
                authorDto.getBrithdate(),
                authorDto.getFirstname(),
                authorDto.getLastname(),
                authorDto.isIsaktive()
        );
    }
}
