package com.example.a.service;

import com.example.a.dto.requestDto.UserRequestDto;
import com.example.a.dto.response.UserResponse;
import com.example.a.dto.response.WriterResponse;
import com.example.a.entity.Users;
import com.example.a.entity.Writer;
import com.example.a.repository.UserRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final WriterService service;


    public UserResponse add(UserRequestDto dto) {
        Optional<Users> users = repository.findByPhoneNumber(dto.getPhoneNumber());

        if (users.isPresent()) {
            throw new IllegalArgumentException(String.format("this number is registered  %s", dto.getPhoneNumber()));
        }
        Users user = Users.of(dto);
        Users save = repository.save(user);
        return UserResponse.from(save);


    }

    @Transactional
    public void delete(Integer userId) {
        repository.deleteById(userId);
    }

    public UserResponse getUser(Integer userId) {
        Optional<Users> byId = repository.findById(userId);
        if (byId.isPresent()) {
            Users users = byId.get();
            UserResponse from = UserResponse.from(users);
            from.setWriterList(convert(service.getWriterListByUserId(userId)));
            return from;
        }
        return null;
    }
    public Boolean updateUser(Integer userId, UserRequestDto request){
        Users users = repository.findById(userId).get();
        if (!StringUtils.isEmpty(request.getFirstname())) users.setFirstname(request.getFirstname());
        if (!StringUtils.isEmpty(request.getPhoneNumber()))users.setPhoneNumber(request.getPhoneNumber());
        if (!StringUtils.isEmpty(String.valueOf(request.getAge())))users.setAge(request.getAge());
        else {
            return false;
        }
        repository.save(users);
        return true;
    }

    private List<WriterResponse> convert(List<Writer> writers) {
        return writers.stream().map(writer -> {
            WriterResponse writerResponse = WriterResponse.from(writer);
            WriterResponse writer1 = service.getWriter(writerResponse.getId());
            writerResponse.setBookResponseList(writer1.getBookResponseList());
            return writerResponse;
        }).toList();
    }


}
