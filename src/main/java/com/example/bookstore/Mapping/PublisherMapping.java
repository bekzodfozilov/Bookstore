package com.example.bookstore.Mapping;

import com.example.bookstore.Dao.Book;
import com.example.bookstore.Dao.Publisher;
import com.example.bookstore.Dto.AdresDto;
import com.example.bookstore.Dto.BookDto;
import com.example.bookstore.Dto.PublisherDto;

import java.util.List;
import java.util.stream.Collectors;

public class PublisherMapping {
    public static PublisherDto ToDto(Publisher publisher) {
        AdresDto adresDto = AdresMapping.ToDto(publisher.getAdres());
        return new PublisherDto(
                publisher.getId(),
                publisher.getName(),
                adresDto,
                publisher.isIsaktive()

        );
    }

    public static PublisherDto ToDtoS(Publisher publisher) {
        AdresDto adresDto = AdresMapping.ToDto(publisher.getAdres());
        List<BookDto> bookDtos = publisher.getBooks()
                .stream()
                .filter(Book::isIsaktive)
                .map(BookMapping::ToDtoP)
                .collect(Collectors.toList());
        return new PublisherDto(
                publisher.getId(),
                publisher.getName(),
                adresDto,
                bookDtos,
                publisher.isIsaktive()

        );
    }


    public static Publisher ToEtity(PublisherDto publisherDto) {
        return new Publisher(
                publisherDto.getId(),
                publisherDto.getName(),
                publisherDto.isIsaktive()
        );
    }
}
