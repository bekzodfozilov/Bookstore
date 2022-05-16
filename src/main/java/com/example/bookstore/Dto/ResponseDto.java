package com.example.bookstore.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto<T> {
    private boolean success;
    private Integer code;
    private String massege;
    private T data;
    private List<ValidatorDto> errors;

    public ResponseDto(boolean success, Integer code, String massege, T data) {
        this.success = success;
        this.code = code;
        this.massege = massege;
        this.data = data;
    }
}
