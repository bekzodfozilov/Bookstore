package com.example.bookstore.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublisherDto {

    private Integer id;

    private String name;

    private AdresDto adresDto;

    private List<BookDto> bookDtos;

    private boolean isaktive;

    public PublisherDto(Integer id, String name, AdresDto adresDto, boolean isaktive) {
        this.id = id;
        this.name = name;
        this.adresDto = adresDto;
        this.isaktive = isaktive;
    }
}
