package com.example.a.repository;

import com.example.a.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users,Integer> {

    @Query(value = "DELETE FROM users WHERE id =:id",nativeQuery = true)
    void deleteByUserId(@Param("id")Integer id);
    Optional<Users>findByPhoneNumber(String phoneNumber);


}
