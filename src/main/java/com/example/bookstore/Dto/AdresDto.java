package com.example.bookstore.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdresDto {

    private Integer id;

    private Integer district_id;

    private Integer region_id;

    private String street;

    private boolean isaktive;
}
