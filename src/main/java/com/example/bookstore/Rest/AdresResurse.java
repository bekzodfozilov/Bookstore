package com.example.bookstore.Rest;

import com.example.bookstore.Dto.AdresDto;
import com.example.bookstore.Dto.ResponseDto;
import com.example.bookstore.Servise.AdresServise;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("adress")
public class AdresResurse {

    private final AdresServise adresServise;

    @GetMapping("get-all-adres")
    private ResponseDto<Page<AdresDto>> getAllAdres(@RequestParam(defaultValue = "20") Integer size, @RequestParam(defaultValue = "0") Integer page){
        return adresServise.getAllAdres(page,size);
    }

    @GetMapping("get-all-adres-params")
    private ResponseDto<?> getAllParams(@RequestParam MultiValueMap<String,String> params){
        return adresServise.getAllAdresParams(params);
    }

    @PostMapping("add-adres")
    private ResponseDto<AdresDto> addAdres(@RequestBody AdresDto adresDto){
        return adresServise.addAdres(adresDto);
    }

    @PutMapping("update-adres")
    private ResponseDto<AdresDto> updateAdres(@RequestBody AdresDto adresDto){
        return adresServise.updateAdres(adresDto);
    }
    @PutMapping("update-isaktive")
    private ResponseDto<AdresDto> updateIsaktive(@RequestParam Integer id){
        return adresServise.updateIsaktive(id);
    }


}
