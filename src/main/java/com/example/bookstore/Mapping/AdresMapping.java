package com.example.bookstore.Mapping;

import com.example.bookstore.Dao.Adres;
import com.example.bookstore.Dto.AdresDto;
import org.springframework.data.domain.Page;

public class AdresMapping {
    public static AdresDto ToDto(Adres adres) {
        return new AdresDto(
                adres.getId(),
                adres.getDistrict_id(),
                adres.getRegion_id(),
                adres.getStreet(),
                adres.isIsaktive()
        );
    }
    public static Adres ToEntitiy(AdresDto adresDto){
        return new Adres(
                adresDto.getId(),
                adresDto.getDistrict_id(),
                adresDto.getRegion_id(),
                adresDto.getStreet(),
                adresDto.isIsaktive()
        );
    }


}
