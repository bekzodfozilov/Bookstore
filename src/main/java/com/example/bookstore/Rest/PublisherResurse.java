package com.example.bookstore.Rest;

import com.example.bookstore.Dto.PublisherDto;
import com.example.bookstore.Dto.ResponseDto;
import com.example.bookstore.Servise.PublisherServise;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("publisher")
public class PublisherResurse {

    private final PublisherServise publisherServise;

    @GetMapping("get-all-publisher")
    private ResponseDto<Page<PublisherDto>> getAllPublisher(@RequestParam(defaultValue = "20") Integer size , @RequestParam(defaultValue = "0") Integer page){
        return publisherServise.getAllPublisher(page,size);
    }

    @GetMapping("get-all-publisher-params")
    private ResponseDto<?> getAllPublisherParams(@RequestParam MultiValueMap<String,String> params){
        return publisherServise.getAllPublisherParams(params);
    }

    @PostMapping("add-publisher")
    private ResponseDto<PublisherDto> addPublisher(@RequestBody PublisherDto publisherDto){
        return publisherServise.addPublisher(publisherDto);
    }

    @PutMapping("update-publisher")
    private ResponseDto<PublisherDto> updatePublisher(@RequestBody PublisherDto publisherDto){
        return publisherServise.updatePublisher(publisherDto);
    }

    @PutMapping("update-isaktive-publisher")
    private ResponseDto<PublisherDto> updateIsaktivePublisher(@RequestParam Integer id){
        return publisherServise.updateIsaktivePublisher(id);
    }

}

