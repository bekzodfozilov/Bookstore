package com.example.bookstore.Rest;

import com.example.bookstore.Dto.AuthorDto;
import com.example.bookstore.Dto.ResponseDto;
import com.example.bookstore.Servise.AuthorServise;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("author")
public class AuthorResurse {

    private final AuthorServise authorServise;

    @GetMapping("get-all-author")
    public ResponseDto<Page<AuthorDto>> getAllAuthor(@RequestParam(defaultValue = "0") Integer page , @RequestParam(defaultValue = "20") Integer size){
        return authorServise.getAllAuthor(page,size);
    }

    @GetMapping("get-all-author-params")
    public ResponseDto<?> getAllAuthorPrams(@RequestParam MultiValueMap<String,String> params){
        return authorServise.getAllAuthorParams(params);
    }

    @PostMapping("add-author")
    public ResponseDto<AuthorDto> addAuthors(@RequestBody AuthorDto authorDto){
        return authorServise.addAuthors(authorDto);
    }

    @PutMapping("Update-author")
    public ResponseDto<AuthorDto> updateAuthor(@RequestBody AuthorDto authorDto){
        return authorServise.updateAuthor(authorDto);
    }
    @PutMapping("Update-isaktive")
    public ResponseDto<AuthorDto> updateIsaktive(@RequestParam Integer id){
        return authorServise.updateIsaktive(id);
    }
}
