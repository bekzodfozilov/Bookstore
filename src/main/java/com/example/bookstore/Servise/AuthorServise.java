package com.example.bookstore.Servise;

import com.example.bookstore.Dao.Author;
import com.example.bookstore.Dto.AuthorDto;
import com.example.bookstore.Dto.ResponseDto;
import com.example.bookstore.Dto.ValidatorDto;
import com.example.bookstore.Helper.Constants.AppResponseCode;
import com.example.bookstore.Helper.Constants.AppResponseMassage;
import com.example.bookstore.Helper.Validator;
import com.example.bookstore.Mapping.AuthorMapping;
import com.example.bookstore.Repository.AuthorRepositry;
import com.example.bookstore.Repository.CustemRepository.CustemAuthorRepositry;
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
public class AuthorServise {

    private final AuthorRepositry authorRepositry;

    private final CustemAuthorRepositry custemAuthorRepositry;

    public ResponseDto<Page<AuthorDto>> getAllAuthor(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Author> authors = authorRepositry.findAll(pageRequest);
        List<AuthorDto> authorDtos = authors
                .stream()
                .filter(Author::isIsaktive)
                .map(AuthorMapping::ToDtoS)
                .collect(Collectors.toList());

        if (authorDtos.size() > 0) {
            return new ResponseDto<>(
                    true, AppResponseCode.OK, AppResponseMassage.OK, new PageImpl<>(authorDtos, authors.getPageable(), authors.getTotalElements())
            );
        }
        return new ResponseDto<>(false,AppResponseCode.DATABASE_ERROR,AppResponseMassage.DATABASE_ERROR,null);
    }

    public ResponseDto<?> getAllAuthorParams(MultiValueMap<String, String> params) {
        Optional<?> authors = custemAuthorRepositry.getAllAuthor(params);
        if(authors.isPresent())
            return new ResponseDto<>(true, AppResponseCode.OK, AppResponseMassage.OK, authors.get());
        return new ResponseDto<>(false,AppResponseCode.DATABASE_ERROR,AppResponseMassage.DATABASE_ERROR,null);
    }

    public ResponseDto<AuthorDto> addAuthors(AuthorDto authorDto) {
        List<ValidatorDto> errors = Validator.AuthorValidator(authorDto);
        if(errors.size() > 0){
            return new ResponseDto<>(false,AppResponseCode.VALIDATION_ERROS,AppResponseMassage.VALIDATION_ERROR,authorDto,errors);
        }
        Author author = AuthorMapping.ToEntity(authorDto);
        try {
            author.setId(null);
            authorRepositry.save(author);
        }catch (Exception e){
            return new ResponseDto<>(false,AppResponseCode.DATABASE_ERROR,AppResponseMassage.DATABASE_ERROR,authorDto);
        }
        return new ResponseDto<>(true,AppResponseCode.OK,AppResponseMassage.OK,AuthorMapping.ToDto(author));
    }

    public ResponseDto<AuthorDto> updateAuthor(AuthorDto authorDto) {
        if(authorRepositry.existsById(authorDto.getId())){
            List<ValidatorDto> errors = Validator.AuthorValidator(authorDto);
            if(errors.size() > 0){
                return new ResponseDto<>(false,AppResponseCode.VALIDATION_ERROS,AppResponseMassage.VALIDATION_ERROR,authorDto,errors);
            }
            Author author = AuthorMapping.ToEntity(authorDto);
            try {
                authorRepositry.save(author);
            }catch (Exception e){
                return new ResponseDto<>(false,AppResponseCode.DATABASE_ERROR,AppResponseMassage.DATABASE_ERROR,authorDto);
            }
            return new ResponseDto<>(true,AppResponseCode.OK,AppResponseMassage.OK,AuthorMapping.ToDto(author));
        }
        return new ResponseDto<>(false,AppResponseCode.NOT_FOUND,AppResponseMassage.NOT_FOUND,authorDto);
    }

    public ResponseDto<AuthorDto> updateIsaktive(Integer id) {
        if(authorRepositry.existsById(id)){
            authorRepositry.UpdateIsaktive(id);
            return new ResponseDto<>(true,AppResponseCode.OK,AppResponseMassage.OK,AuthorMapping.ToDto(authorRepositry.findById(id).get()));
        }
        return new ResponseDto<>(
                false,AppResponseCode.NOT_FOUND,AppResponseMassage.NOT_FOUND,null
        );
    }
}
