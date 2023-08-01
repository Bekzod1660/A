package com.example.a.controller;

import com.example.a.dto.requestDto.BookRequestDto;
import com.example.a.dto.response.BookResponse;
import com.example.a.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService service;

    @PostMapping("/add")
    public BookResponse add(@RequestBody BookRequestDto dto){
        return service.add(dto);
    }

    @DeleteMapping("/delete/{id}")
    public Boolean delete(@PathVariable Integer id){
        service.delete(id);
        return  true;
    }

    @PutMapping("/update/{id}")
    public Boolean update(@RequestBody BookRequestDto dto, @PathVariable Integer id){
        return service.updateBook(id, dto);
    }

    @GetMapping ("/getBook/{id}")
    public BookResponse getBook(@PathVariable Integer id){
        return service.getByBookId(id);
    }
}
