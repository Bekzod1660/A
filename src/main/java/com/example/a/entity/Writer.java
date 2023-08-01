package com.example.a.entity;

import com.example.a.dto.requestDto.WriterRequestDto;
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
public class Writer extends BaseServerEntity {
    private String direction;

    @Column(name = "user_id")
    private Integer userId;
    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Users users;


    public static Writer of(WriterRequestDto dto){
      return   Writer.builder()
              .userId(dto.getUserId())
                .direction(dto.getDirection())
                .build();
    }
}
