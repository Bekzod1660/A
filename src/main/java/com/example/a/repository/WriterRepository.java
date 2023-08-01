package com.example.a.repository;

import com.example.a.entity.Writer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface WriterRepository extends JpaRepository<Writer,Integer> {

    @Query(value = "SELECT * FROM writer WHERE user_id=:user_id",nativeQuery = true)
    List<Writer>getWriterListByUserId(@Param("user_id") Integer user_id);
}
