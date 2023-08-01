package com.example.a.controller;

import com.example.a.dto.requestDto.WriterRequestDto;
import com.example.a.dto.response.WriterResponse;
import com.example.a.service.WriterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/writer")
@RequiredArgsConstructor
public class WriterController {
    private final WriterService service;

    @PostMapping("/add")
    public WriterResponse add(@RequestBody WriterRequestDto dto){
        return service.add(dto);
    }

    @DeleteMapping("/delete/{id}")
    public Boolean delete(@PathVariable Integer id){
        service.delete(id);
        return true;
    }

    @PutMapping("/update/{id}")
    public Boolean update(@RequestBody WriterRequestDto dto,@PathVariable Integer id){
        return service.updateWriter(id, dto);
    }
    @GetMapping("/getWriter/{id}")
    public WriterResponse getWriter(@PathVariable Integer id){
        return service.getWriter(id);
    }
}
