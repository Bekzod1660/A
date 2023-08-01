package com.example.a.service;


import com.example.a.dto.requestDto.BookRequestDto;
import com.example.a.dto.response.BookResponse;
import com.example.a.entity.Book;
import com.example.a.repository.BookRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository repository;

    @Transactional
    public BookResponse add(BookRequestDto dto) {

        Book of = Book.of(dto);
        Book book = repository.save(of);
        return BookResponse.from(book);

    }
    public void delete(Integer id){
        repository.deleteById(id);
    }

    public BookResponse getByBookId(Integer bookId){
        Book book = repository.findById(bookId).orElseThrow(() -> new IllegalArgumentException("no such book found"));
        return BookResponse.from(book);
    }

    public Boolean updateBook(Integer bookId,BookRequestDto dto){
        Book book = repository.findById(bookId).get();
        if (!StringUtils.isEmpty(dto.getName())) book.setName(dto.getName());
        repository.save(book);
        return true;

    }
    public List<Book>getBookListByWriterId(Integer writerId){
       return repository.getBookListByWriterId(writerId);
    }
}
