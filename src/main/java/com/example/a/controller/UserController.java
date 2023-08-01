package com.example.a.controller;

import com.example.a.dto.requestDto.UserRequestDto;
import com.example.a.dto.response.UserResponse;
import com.example.a.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @PostMapping("/add")
    public UserResponse createUser(@RequestBody  UserRequestDto dto) {

        return service.add(dto);

    }
    @DeleteMapping("/delete/{id}")
    public Boolean deleted(@PathVariable Integer id){
        service.delete(id);
        return true;

    }
    @PutMapping("/update/{userId}")
    public Boolean update(@RequestBody UserRequestDto dto,@PathVariable Integer userId){
      return   service.updateUser(userId,dto);
    }
    @GetMapping("/getUser/{id}")
    public UserResponse getUser(@PathVariable Integer id){
        return service.getUser(id);
    }

}
