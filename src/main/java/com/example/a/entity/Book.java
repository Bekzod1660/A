package com.example.a.entity;

import com.example.a.dto.requestDto.BookRequestDto;
import com.example.a.entity.base.BaseServerEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Book extends BaseServerEntity {
    private String name;
    @Column(name = "writer_id")
    private Integer writerId;

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "writer_id", insertable = false, updatable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Writer writer;

    public static Book of(BookRequestDto dto){
      return   Book.builder()
              .writerId(dto.getWriterId())
                .name(dto.getName())
                .build();
    }
}
