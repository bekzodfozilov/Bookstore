package com.example.bookstore.Servise;

import com.example.bookstore.Dao.Adres;
import com.example.bookstore.Dto.AdresDto;
import com.example.bookstore.Dto.ResponseDto;
import com.example.bookstore.Dto.ValidatorDto;
import com.example.bookstore.Helper.Constants.AppResponseCode;
import com.example.bookstore.Helper.Constants.AppResponseMassage;
import com.example.bookstore.Helper.Validator;
import com.example.bookstore.Mapping.AdresMapping;
import com.example.bookstore.Repository.AdresReposity;
import com.example.bookstore.Repository.CustemRepository.CustemAdresRepositiry;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdresServise {

    private final AdresReposity adresReposity;

    private final CustemAdresRepositiry custemAdresRepositiry;

    public ResponseDto<Page<AdresDto>> getAllAdres(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page,size);
        Page<Adres> adres = adresReposity.findAll(pageRequest);
        List<AdresDto> adresDtos = adres
                .stream()
                .filter(Adres::isIsaktive)
                .map(AdresMapping::ToDto)
                .collect(Collectors.toList());
        if(adresDtos.size() > 0){
            return new ResponseDto<>(
                    true, AppResponseCode.OK, AppResponseMassage.OK,new PageImpl<>(adresDtos,adres.getPageable(),adres.getTotalElements())
            );
        }
        return new ResponseDto<>(false,AppResponseCode.DATABASE_ERROR,AppResponseMassage.DATABASE_ERROR,null);
    }

    public ResponseDto<?> getAllAdresParams(MultiValueMap<String, String> params) {
        Optional<?> adres = custemAdresRepositiry.getAllAdres(params);
        if(adres.isPresent()){
            return new ResponseDto<>(
                    true,AppResponseCode.OK,AppResponseMassage.OK,adres.get()
            );
        }
        return new ResponseDto<>(false,AppResponseCode.DATABASE_ERROR,AppResponseMassage.DATABASE_ERROR,null);
    }

    public ResponseDto<AdresDto> addAdres(AdresDto adresDto) {
        List<ValidatorDto> errors = Validator.AdresValidator(adresDto);
        if(errors.size() > 0){
            return new ResponseDto<>(
                    false,AppResponseCode.VALIDATION_ERROS,AppResponseMassage.VALIDATION_ERROR,adresDto,errors
            );
        }
        Adres adres = AdresMapping.ToEntitiy(adresDto);
        try {
            adres.setId(null);
            adresReposity.save(adres);
        }catch (Exception e){
            return new ResponseDto<>(false,AppResponseCode.DATABASE_ERROR,AppResponseMassage.DATABASE_ERROR,adresDto);
        }
        return new ResponseDto<>(true,AppResponseCode.OK,AppResponseMassage.OK,AdresMapping.ToDto(adres));
    }

    public ResponseDto<AdresDto> updateAdres(AdresDto adresDto) {
        if(adresDto.getId()!= null && adresReposity.existsById(adresDto.getId())){
            Adres adres = null;
            List<ValidatorDto> errors = Validator.AdresValidator(adresDto);
            if (errors.size() > 0){
                return new ResponseDto<>(
                        false,AppResponseCode.VALIDATION_ERROS,AppResponseMassage.VALIDATION_ERROR,adresDto,errors
                );
            }
            try {
                 adres = AdresMapping.ToEntitiy(adresDto);
                adresReposity.save(adres);
            }catch (Exception e){
                return new ResponseDto<>(false,AppResponseCode.DATABASE_ERROR,AppResponseMassage.DATABASE_ERROR,adresDto);
            }
            return new ResponseDto<>(true,AppResponseCode.OK,AppResponseMassage.OK,AdresMapping.ToDto(adres));
        }
        return new ResponseDto<>(false,AppResponseCode.NOT_FOUND,AppResponseMassage.NOT_FOUND,adresDto);
    }

    public ResponseDto<AdresDto> updateIsaktive(Integer id) {
        if(adresReposity.existsById(id)){
            try {
                adresReposity.updateIsaktive(id);
                Optional<Adres> adres = adresReposity.findById(id);
                return new ResponseDto<>(true,AppResponseCode.OK,AppResponseMassage.OK,AdresMapping.ToDto(adres.get()));
            }catch (Exception e){
                return new ResponseDto<>(false,AppResponseCode.DATABASE_ERROR,AppResponseMassage.DATABASE_ERROR,null);
            }
        }
        return new ResponseDto<>(
                false,AppResponseCode.NOT_FOUND,AppResponseMassage.NOT_FOUND,null
        );
    }
}
