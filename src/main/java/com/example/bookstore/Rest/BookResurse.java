package com.example.bookstore.Rest;

import com.example.bookstore.Dto.BookDto;
import com.example.bookstore.Dto.ResponseDto;
import com.example.bookstore.Servise.BookServise;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("book")
public class BookResurse {

    final BookServise bookServise;

    /* Istalgan fildi boyiga get qiladi size page berilga pege xolda qaytaradi agar berilmasa list qaytaradi agar parametir berilmasa
    * Hamm kitoblarni chiqaradi */

    @GetMapping("get-all-params")
    public ResponseDto<?> getAllBooksParams(@RequestParam MultiValueMap<String,String> parmas){
        return bookServise.getAllBooksParams(parmas);
    }

    /* Barcha kitoblarni op keladi va ichidagiaftor va publisherni ham torib keladi*/

    @GetMapping("get-all")
    public ResponseDto<Page<BookDto>> getAllBooks(@RequestParam(defaultValue = "20") Integer size , @RequestParam(defaultValue = "0") Integer page){
        return bookServise.getAllBooks(page,size);
    }

    /*Book add qilish uchun*/

    @PostMapping("add-books")
    public ResponseDto<BookDto> addBooks(@RequestBody BookDto bookDto){
        return bookServise.addBooks(bookDto);
    }

    /* Update qilish uchun idi beriladi va Bookdto kirb keladi bookdto boyicha validatsiyadan otqizb ketin seve qilinadi*/
    @PutMapping("update")
    public ResponseDto<BookDto> update(@RequestParam Integer id , @RequestBody BookDto bookDto){
        return bookServise.update(id,bookDto);
    }
    /* Kiritilgan id dagi malumotni isaktive ustunini false ozgartirb qoyadi*/
    @PutMapping("Delete")
    public ResponseDto<BookDto> DeleteBooks(@RequestParam Integer id){
        return bookServise.delete(id);
    }
}
