package com.example.a.dto.response;



import com.example.a.entity.Writer;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WriterResponse  {
    private Integer id;
    private String direction;
    private List<BookResponse> bookResponseList;

    public static WriterResponse from(Writer writer){
        return  WriterResponse.builder()
                .id(writer.getId())
                .direction(writer.getDirection())
                .build();
    }
}
