package com.example.a.dto.response;



import com.example.a.entity.Book;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookResponse {
    private Integer id;
    private String name;

    public static BookResponse from(Book book){
        return BookResponse.builder()
                .id(book.getId())
                .name(book.getName())
                .build();
    }
}
