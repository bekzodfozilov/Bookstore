package com.example.bookstore.Helper;

import com.example.bookstore.Dto.*;
import com.example.bookstore.Helper.Constants.AppResponseMassage;


import java.util.ArrayList;
import java.util.List;

public class Validator {

    public static List<ValidatorDto> BookValidator(BookDto bookDto) {
        List<ValidatorDto> errors = new ArrayList<>();
        if (bookDto.getNameuz() == null || bookDto.getNameuz().trim().length() < 1) {
            errors.add(new ValidatorDto("nameuz", AppResponseMassage.EMPTY_FIELD));
        }
        if (bookDto.getCost() == null) {
            errors.add(new ValidatorDto("cost", AppResponseMassage.EMPTY_FIELD));
        } else if (bookDto.getCost() < 0) {
            errors.add(new ValidatorDto("cost", AppResponseMassage.MINUS_VALUE));
        }
        if (bookDto.getPublished_date() == null || bookDto.getPublished_date().trim().length() < 1) {
            errors.add(new ValidatorDto("published_date", AppResponseMassage.EMPTY_FIELD));
        }
        if (bookDto.getPage_count() == null) {
            errors.add(new ValidatorDto("page_count", AppResponseMassage.EMPTY_FIELD));
        } else if (bookDto.getPage_count() < 1) {
            errors.add(new ValidatorDto("page_count", AppResponseMassage.MINUS_VALUE));
        }
        if (bookDto.getGenre() == null || bookDto.getGenre().trim().length() < 1) {
            errors.add(new ValidatorDto("genre", AppResponseMassage.EMPTY_FIELD));
        }
        if(!bookDto.isIsaktive()){
            errors.add(new ValidatorDto("isaktive",AppResponseMassage.WRONG_TYPE));
        }
        return errors;
    }


    public static List<ValidatorDto> AuthorValidator(AuthorDto authorDto) {

        List<ValidatorDto> errors = new ArrayList<>();

        if (authorDto.getBrithdate() == null || authorDto.getBrithdate().trim().length() < 1) {
            errors.add(new ValidatorDto("birthdate", AppResponseMassage.EMPTY_FIELD));
        }
        if (authorDto.getFirstname() == null || authorDto.getFirstname().trim().length() < 1) {
            errors.add(new ValidatorDto("firstname", AppResponseMassage.EMPTY_FIELD));
        }
        if (authorDto.getLastname() == null || authorDto.getLastname().trim().length() < 1) {
            errors.add(new ValidatorDto("lastname", AppResponseMassage.EMPTY_FIELD));
        }
        if(!authorDto.isIsaktive()){
            errors.add(new ValidatorDto("isaktive",AppResponseMassage.WRONG_TYPE));
        }
        return errors;
    }

    public static List<ValidatorDto> PublisherValidator(PublisherDto publisherDto) {

        List<ValidatorDto> errors = new ArrayList<>();
        if (publisherDto.getName() == null || publisherDto.getName().trim().length() < 1) {
             errors.add(new ValidatorDto("name",AppResponseMassage.EMPTY_FIELD));
        }
        if(publisherDto.getAdresDto() == null || publisherDto.getAdresDto().getId() == null){
            errors.add(new ValidatorDto("adres_id",AppResponseMassage.EMPTY_FIELD));
        }else if(publisherDto.getAdresDto().getId() < 0){
            errors.add(new ValidatorDto("adres_id",AppResponseMassage.MINUS_VALUE));
        }
        if(!publisherDto.isIsaktive()){
            errors.add(new ValidatorDto("isaktive",AppResponseMassage.WRONG_TYPE));
        }
        return errors;
    }

    public static List<ValidatorDto> AdresValidator(AdresDto adresDto) {
        List<ValidatorDto> errors = new ArrayList<>();

        if(adresDto.getDistrict_id() == null){
            errors.add(new ValidatorDto("district_id",AppResponseMassage.EMPTY_FIELD));
        }else if(adresDto.getDistrict_id() < 1){
            errors.add(new ValidatorDto("district_id",AppResponseMassage.MINUS_VALUE));
        }
        if(adresDto.getRegion_id() == null){
            errors.add(new ValidatorDto("region_id",AppResponseMassage.EMPTY_FIELD));
        }else if(adresDto.getRegion_id() < 1){
            errors.add(new ValidatorDto("region_id",AppResponseMassage.MINUS_VALUE));
        }
        if(adresDto.getStreet() == null){
            errors.add(new ValidatorDto("street",AppResponseMassage.EMPTY_FIELD));
        }else if(adresDto.getStreet().trim().length() < 1){
            errors.add(new ValidatorDto("street",AppResponseMassage.EMPTY_FIELD));
        }if(!adresDto.isIsaktive()){
            errors.add(new ValidatorDto("isaktive",AppResponseMassage.WRONG_TYPE));
        }
        return errors;
    }
}
