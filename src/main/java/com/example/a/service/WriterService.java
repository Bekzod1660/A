package com.example.a.service;

import com.example.a.dto.requestDto.UserRequestDto;
import com.example.a.dto.requestDto.WriterRequestDto;
import com.example.a.dto.response.BookResponse;
import com.example.a.dto.response.UserResponse;
import com.example.a.dto.response.WriterResponse;
import com.example.a.entity.Book;
import com.example.a.entity.Users;
import com.example.a.entity.Writer;
import com.example.a.repository.UserRepository;
import com.example.a.repository.WriterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import io.micrometer.common.util.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WriterService {
    private final WriterRepository repository;
    private final BookService service;


    public WriterResponse add(WriterRequestDto dto) {
        Writer newWriter = Writer.of(dto);


        Writer writer = repository.save(newWriter);
        return WriterResponse.from(writer);

    }

    public List<Writer> getWriterListByUserId(Integer userId) {
        return repository.getWriterListByUserId(userId);
    }

    public void delete(Integer writerId) {
        repository.deleteById(writerId);
    }

    public WriterResponse getWriter(Integer writerId) {
        Optional<Writer> byId = repository.findById(writerId);
        if (byId.isPresent()) {
            Writer writer = byId.get();
            WriterResponse from = WriterResponse.from(writer);
            from.setBookResponseList(convert(service.getBookListByWriterId(writerId)));
            return from;
        }
        return null;
    }

    public Boolean updateWriter(Integer writerId, WriterRequestDto dto) {
        Writer writer = repository.findById(writerId).get();
        if (!StringUtils.isEmpty(dto.getDirection())) writer.setDirection(dto.getDirection());
        else {
            return false;
        }
        repository.save(writer);
        return true;
    }

    private List<BookResponse> convert(List<Book> booksList) {
        return booksList.stream().map(book -> {
            BookResponse bookResponse = BookResponse.from(book);
            return bookResponse;
        }).toList();
    }

}
