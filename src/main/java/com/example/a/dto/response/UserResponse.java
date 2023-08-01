package com.example.a.dto.response;


import com.example.a.entity.Users;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse  {
    private Integer id;
    private String firstname;
    private String phoneNumber;
    private int age;
    private List<WriterResponse> writerList;
    public static UserResponse from(Users users){
        return UserResponse.builder()
                .id(users.getId())
                .age(users.getAge())
                .firstname(users.getFirstname())
                .phoneNumber(users.getPhoneNumber())
                .build();
    }
}
