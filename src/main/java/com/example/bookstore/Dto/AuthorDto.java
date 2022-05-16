package com.example.bookstore.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDto {

    private Integer id;

    private String brithdate;

    private String firstname;

    private String lastname;

    private List<BookDto> bookDtos;

    private boolean isaktive;

    public AuthorDto(Integer id, String brithdate, String firstname, String lastname , boolean isaktive) {
        this.id = id;
        this.brithdate = brithdate;
        this.firstname = firstname;
        this.lastname = lastname;
        this.isaktive = isaktive;
    }
}
