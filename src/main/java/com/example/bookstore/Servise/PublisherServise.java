package com.example.bookstore.Servise;

import com.example.bookstore.Dao.Adres;
import com.example.bookstore.Dao.Publisher;
import com.example.bookstore.Dto.PublisherDto;
import com.example.bookstore.Dto.ResponseDto;
import com.example.bookstore.Dto.ValidatorDto;
import com.example.bookstore.Helper.Constants.AppResponseCode;
import com.example.bookstore.Helper.Constants.AppResponseMassage;
import com.example.bookstore.Helper.Validator;
import com.example.bookstore.Mapping.PublisherMapping;
import com.example.bookstore.Repository.AdresReposity;
import com.example.bookstore.Repository.CustemRepository.CustemPublisherRepository;
import com.example.bookstore.Repository.PublisherRepositry;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PublisherServise {

    private final PublisherRepositry publisherRepositry;

    private final CustemPublisherRepository custemPublisherRepository;

    private final AdresReposity adresReposity;

    public ResponseDto<Page<PublisherDto>> getAllPublisher(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Publisher> publishers = publisherRepositry.findAll(pageRequest);
        List<PublisherDto> publisherDtos = publishers
                .stream()
                .filter(Publisher::isIsaktive)
                .map(PublisherMapping::ToDtoS)
                .collect(Collectors.toList());
        if (publisherDtos.size() > 0) {
            return new ResponseDto<>(
                    true, AppResponseCode.OK, AppResponseMassage.OK, new PageImpl<>(publisherDtos, publishers.getPageable(), publishers.getTotalElements())
            );
        }
        return new ResponseDto<>(false, AppResponseCode.NOT_FOUND, AppResponseMassage.NOT_FOUND, null);

    }

    public ResponseDto<?> getAllPublisherParams(MultiValueMap<String, String> params) {
        Optional<?> publisher = custemPublisherRepository.getAllPublisher(params);

        if (publisher.isPresent()) {
            return new ResponseDto<>(
                    true, AppResponseCode.OK, AppResponseMassage.OK, publisher.get());
        }
        return new ResponseDto<>(false, AppResponseCode.DATABASE_ERROR, AppResponseMassage.DATABASE_ERROR, null);
    }

    public ResponseDto<PublisherDto> addPublisher(PublisherDto publisherDto) {
        List<ValidatorDto> errors = Validator.PublisherValidator(publisherDto);
        if (errors.size() > 0) {
            return new ResponseDto<>(
                    false, AppResponseCode.VALIDATION_ERROS, AppResponseMassage.VALIDATION_ERROR, publisherDto, errors
            );
        }
        if (adresReposity.existsById(publisherDto.getAdresDto().getId())) {
            Optional<Adres> adres = adresReposity.findById(publisherDto.getAdresDto().getId());

            if (adres.isPresent()) {
                Publisher publisher = PublisherMapping.ToEtity(publisherDto);
                publisher.setAdres(adres.get());
                try {
                    publisher.setId(null);
                    publisherRepositry.save(publisher);
                } catch (Exception e) {
                    return new ResponseDto<>(
                            false, AppResponseCode.DATABASE_ERROR, AppResponseMassage.DATABASE_ERROR, publisherDto
                    );
                }

                return new ResponseDto<>(true, AppResponseCode.OK, AppResponseMassage.OK, PublisherMapping.ToDto(publisher));
            }
        }
        return new ResponseDto<>(false, AppResponseCode.NOT_FOUND, AppResponseMassage.NOT_FOUND, publisherDto);
    }

    public ResponseDto<PublisherDto> updatePublisher(PublisherDto publisherDto) {
        if (publisherRepositry.existsById(publisherDto.getId())) {
            List<ValidatorDto> errosr = Validator.PublisherValidator(publisherDto);
            if (errosr.size() > 0) {
                return new ResponseDto<>(false, AppResponseCode.VALIDATION_ERROS, AppResponseMassage.VALIDATION_ERROR, publisherDto, errosr);
            }
            if (adresReposity.existsById(publisherDto.getAdresDto().getId())) {
                Optional<Adres> adres = adresReposity.findById(publisherDto.getAdresDto().getId());
                Publisher publisher = PublisherMapping.ToEtity(publisherDto);
                publisher.setAdres(adres.get());
                try {
                    publisherRepositry.save(publisher);
                } catch (Exception e) {
                    return new ResponseDto<>(
                            false, AppResponseCode.DATABASE_ERROR, AppResponseMassage.DATABASE_ERROR, publisherDto
                    );
                }
                return new ResponseDto<>(true, AppResponseCode.OK, AppResponseMassage.OK, PublisherMapping.ToDto(publisher));
            }
        }
        return new ResponseDto<>(false, AppResponseCode.NOT_FOUND, AppResponseMassage.NOT_FOUND, publisherDto);
    }

    public ResponseDto<PublisherDto> updateIsaktivePublisher(Integer id) {
        if(publisherRepositry.existsById(id)){
            publisherRepositry.updateIsaktive(id);
            return new ResponseDto<>(
                    true,AppResponseCode.OK,AppResponseMassage.OK,PublisherMapping.ToDto(publisherRepositry.findById(id).get())
            );
        }
        return new ResponseDto<>(false,AppResponseCode.NOT_FOUND,AppResponseMassage.NOT_FOUND,null);
    }
}
