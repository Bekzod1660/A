package com.example.a.repository;

import com.example.a.entity.Book;
import com.example.a.entity.Writer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Integer> {
    @Query(value = "SELECT * FROM book WHERE writer_id=:writer_id",nativeQuery = true)
    List<Book> getBookListByWriterId(@Param("writer_id") Integer writer_id);
}
