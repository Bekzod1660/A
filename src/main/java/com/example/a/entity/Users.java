package com.example.a.entity;

import com.example.a.dto.requestDto.UserRequestDto;
import com.example.a.entity.base.BaseServerEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Users extends BaseServerEntity {

    private String firstname;
    @Column(unique = true)
    private String phoneNumber;
    private int age;

    public static Users of(UserRequestDto dto){
      return Users.builder()
               .age(dto.getAge())
               .firstname(dto.getFirstname())
               .phoneNumber(dto.getPhoneNumber())
               .build();
    }

}
