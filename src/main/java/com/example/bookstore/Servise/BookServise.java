package com.example.bookstore.Servise;

import com.example.bookstore.Dao.Author;
import com.example.bookstore.Dao.Book;
import com.example.bookstore.Dao.Publisher;
import com.example.bookstore.Dto.BookDto;
import com.example.bookstore.Dto.ResponseDto;
import com.example.bookstore.Dto.ValidatorDto;
import com.example.bookstore.Helper.Constants.AppResponseCode;
import com.example.bookstore.Helper.Constants.AppResponseMassage;
import com.example.bookstore.Helper.Validator;
import com.example.bookstore.Mapping.BookMapping;
import com.example.bookstore.Repository.AuthorRepositry;
import com.example.bookstore.Repository.BookRepository;
import com.example.bookstore.Repository.CustemRepository.CustemBookRepository;
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
public class BookServise {

    private final BookRepository bookRepository;

    private final CustemBookRepository custemBookRepository;

    private final AuthorRepositry authorRepositry;

    private final PublisherRepositry publisherRepositry;



    public ResponseDto<?> getAllBooksParams(MultiValueMap<String, String> parmas) {
        Optional<?> books = custemBookRepository.getAllBooks(parmas);
        if(books.isPresent()){
            return new ResponseDto<>(true, AppResponseCode.OK, AppResponseMassage.OK,books.get());
        }
        return new ResponseDto<>(false,AppResponseCode.DATABASE_ERROR,AppResponseMassage.DATABASE_ERROR,null);
    }

    public ResponseDto<Page<BookDto>> getAllBooks( Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page,size);
        Page<Book> books = bookRepository.findAll(pageRequest);
        List<BookDto> bookDtos = books.stream()
                .filter(Book::isIsaktive)
                .map(BookMapping::ToDto)
                .collect(Collectors.toList());
        if(bookDtos.size() > 0){
            return new ResponseDto<>(
                    true,AppResponseCode.OK,AppResponseMassage.OK,new PageImpl<>(bookDtos,books.getPageable(),books.getTotalElements())
            );
        }
        return new ResponseDto<>(false,AppResponseCode.DATABASE_ERROR,AppResponseMassage.DATABASE_ERROR,null);
    }

    public ResponseDto<BookDto> addBooks(BookDto bookDto) {
        /*Kirb kelgan Bookdto dan fildal valid ekanligini tekshiradi agar fildlar null yani bosh bolasa qaysi fild null ekanligini aytb error qaytaradi*/
        List<ValidatorDto> errors = Validator.BookValidator(bookDto);
        if(errors.size() > 0){
            return new ResponseDto<>(false,AppResponseCode.VALIDATION_ERROS,AppResponseMassage.VALIDATION_ERROR,bookDto,errors);
        }
        Book book = BookMapping.ToEntity(bookDto);
        Optional<Author> author = authorRepositry.findById(bookDto.getAuthorDto().getId());
        if(author.isEmpty()){
             return new ResponseDto<>(false,AppResponseCode.DATABASE_ERROR,AppResponseMassage.DATABASE_ERROR,bookDto);
        }
        book.setAuthor(author.get());
        Optional<Publisher> publisher = publisherRepositry.findById(bookDto.getPublisherDto().getId());
        if (publisher.isEmpty()){
            return new ResponseDto<>(false,AppResponseCode.DATABASE_ERROR,AppResponseMassage.DATABASE_ERROR,bookDto);
        }
        book.setPublisher(publisher.get());
        try {
            book.setId(null);
            bookRepository.save(book);
        }catch (Exception ignored){
            return new ResponseDto<>(
                    false,AppResponseCode.DATABASE_ERROR,AppResponseMassage.DATABASE_ERROR,bookDto);
        }
        return new ResponseDto<>(true,AppResponseCode.OK,AppResponseMassage.OK,BookMapping.ToDto(book));
    }

    public ResponseDto<BookDto> update(Integer id, BookDto bookDto) {
        if(bookRepository.existsById(id)){
            List<ValidatorDto> errors = Validator.BookValidator(bookDto);
            if(errors.size() > 0){
                return new ResponseDto<>(false,AppResponseCode.VALIDATION_ERROS,AppResponseMassage.VALIDATION_ERROR,bookDto,errors);
            }
            Book book = BookMapping.ToEntity(bookDto);
            Optional<Author> author = authorRepositry.findById(bookDto.getAuthorDto().getId());
            if(author.isEmpty()){
                return new ResponseDto<>(
                        false,AppResponseCode.DATABASE_ERROR,AppResponseMassage.DATABASE_ERROR,bookDto
                );
            }
            book.setAuthor(author.get());
            Optional<Publisher> publisher = publisherRepositry.findById(bookDto.getPublisherDto().getId());
            if(publisher.isEmpty()){
                return new ResponseDto<>(
                        false,AppResponseCode.DATABASE_ERROR,AppResponseMassage.DATABASE_ERROR,bookDto
                );
            }
            book.setPublisher(publisher.get());
            try {
                bookRepository.save(book);
            }catch (Exception e){
                return new ResponseDto<>(
                        false,AppResponseCode.DATABASE_ERROR,AppResponseMassage.DATABASE_ERROR,bookDto
                );
            }
         return new ResponseDto<>(true,AppResponseCode.OK,AppResponseMassage.OK,BookMapping.ToDto(book));
        }
        return new ResponseDto<>(
                false,AppResponseCode.NOT_FOUND,AppResponseMassage.NOT_FOUND,bookDto
        );
    }

    public ResponseDto<BookDto> delete(Integer id) {
     if (bookRepository.existsById(id)){
            bookRepository.uptade(id);
             return new ResponseDto<>(true, AppResponseCode.OK, AppResponseMassage.OK, BookMapping.ToDto(bookRepository.findById(id).get()));
     }
        return new ResponseDto<>(false,AppResponseCode.NOT_FOUND,AppResponseMassage.NOT_FOUND,null);
    }
}
